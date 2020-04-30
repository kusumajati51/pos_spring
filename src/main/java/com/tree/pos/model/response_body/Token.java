package com.tree.pos.model.response_body;


import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token implements Serializable{

    @SerializedName("token")
    @Expose
    private String token;


    public Token(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}