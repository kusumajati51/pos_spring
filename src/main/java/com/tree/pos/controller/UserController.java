package com.tree.pos.controller;

import javax.validation.Valid;

import com.tree.pos.model.User;
import com.tree.pos.service.model.UserService;
import com.tree.pos.validators.custom.UniqueValidatorUserEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    
    @Autowired  
    UserService userService;

    @Autowired
    UniqueValidatorUserEmail validator;

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

    // @GetMapping(path = "/get/{name}")
    // @ResponseStatus(HttpStatus.OK)
    // public @ResponseBody User findUser(@PathVariable String name ){
    //   return userService.findByName(name);
    // }

    @GetMapping(path = "/getEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody User findUserByEmail(@PathVariable String email ){
      return userService.findByEmail(email);
    }
}