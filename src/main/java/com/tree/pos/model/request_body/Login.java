package com.tree.pos.model.request_body;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tree.pos.service.model.UserService;
import com.tree.pos.validators.anotation.Exist;

public class Login implements Serializable{

    @SerializedName("email")
    @Expose
    @Exist(service = UserService.class,fieldName = "email", message = "email not found")
    private String email;
    @SerializedName("password")
    @Expose
    private String password;


    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    
    public String getEmail() {
    return email;
    }
    
    public void setEmail(final String email) {
    this.email = email;
    }
    
    public String getPassword() {
    return password;
    }
    
    public void setPassword(final String password) {
    this.password = password;
    }
}