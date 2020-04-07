package com.tree.pos.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tree.pos.service.model.UserService;
import com.tree.pos.validators.anotation.*;

@Entity
@Table(name = "user")
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5133788788349982966L;

    /**
     *
     */
    @Id
    @GeneratedValue
    @SerializedName("id")
    @Expose
    private long id ;


    @SerializedName("name")
    @NotEmpty(message = "Please provide a name")
    @Expose 
    private String name;

    @SerializedName("email")
    @NotEmpty(message = "{Please provide a email}")
    @Expose
    @Unique(service = UserService.class, fieldName = "email", message = "this email has been taken")
    private String email;

    @SerializedName("password")
    @NotEmpty(message = "Please provide password")
    @Expose  
    @ValidPassword
    private String password;

    // @Transient
    // @NotEmpty(message = "Please Reapet your password")
    // private String confrimPassword;


    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // public String getConfrimPassword() {
    //     return this.confrimPassword;
    // }

    // public void setConfrimPassword(String confrimPassword) {
    //     this.confrimPassword = confrimPassword;
    // }

    
    @Override
    public String toString() {
        return "{" +
            " \"id\":\"" + getId() + "\"" +
            ", \"name\":\"" + getName() + "\"" +
            ", \"email\":\"" + getEmail() + "\"" +
            ", \"password\":\"" + getPassword() + "\"" +
            "}";
    }

}