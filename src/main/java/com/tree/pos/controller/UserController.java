package com.tree.pos.controller;

import javax.validation.Valid;

import com.tree.pos.model.User;
import com.tree.pos.service.model.UserService;
import com.tree.pos.validators.custom.UniqueValidatorUserEmail;
import com.tree.pos.validators.intrefaces.CobaSalah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    
    @Autowired 
    UserService userService;

   

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @GetMapping("/employees")
    @ResponseBody public String all() {
      return "loha loha";
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public @ResponseBody String addUser(@Valid @RequestBody final User user){
        user.setPassword(bcrypt.encode(user.getPassword()));
        userService.save(user);
        return user.toString();

    } 
}