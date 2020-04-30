package com.tree.pos.controller;

import javax.validation.Valid;

import com.google.gson.Gson;
import com.tree.pos.model.User;
import com.tree.pos.service.model.UserService;
import com.tree.pos.validators.custom.UniqueValidatorUserEmail;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    
    @Autowired 
    UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    
    Gson gson = new Gson();

    @GetMapping("/")
    @ResponseBody public String all() {
      return "loha loha";
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public @ResponseBody String addUser(@Valid @RequestBody final User user)throws JSONException {
        JSONObject json;
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userService.save(user);
        json = new JSONObject(gson.toJson(user));
        // json.remove("confirmPassword");
        return json.toString();

    } 
}