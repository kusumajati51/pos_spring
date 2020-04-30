package com.tree.pos.controller;

import javax.validation.Valid;

import com.google.gson.Gson;
import com.tree.pos.auth.token.JwtTokenUtil;
import com.tree.pos.model.User;
import com.tree.pos.service.model.UserService;
import com.tree.pos.model.request_body.Login;
import com.tree.pos.model.response_body.Token;
import com.tree.pos.validators.custom.UniqueValidatorUserEmail;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired 
    UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    Gson gson = new Gson();

    @GetMapping("/")
    @ResponseBody public String all() {
      return "loha loha";
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Login login) throws Exception{
      authenticate(login.getEmail(), login.getPassword());
      final UserDetails userDetails = userService.loadUserByUsername(login.getEmail());
      final String token = jwtTokenUtil.generatedToken(userDetails);
      return ResponseEntity.ok(new Token(token));
    }

    @PostMapping(path = "/register")
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

    private void authenticate(String email, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch(DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }
}