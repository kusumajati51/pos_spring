package com.tree.pos.model.request_body;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login implements Serializable{

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    public Login(){
        
    }
    
    public String getEmail() {
    return email;
    }
    
    public void setEmail(String email) {
    this.email = email;
    }
    
    public String getPassword() {
    return password;
    }
    
    public void setPassword(String password) {
    this.password = password;
    }
}