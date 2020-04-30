package com.tree.pos.model;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tree.pos.service.model.UserService;
import com.tree.pos.validators.anotation.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "user")
@MatcherValidator( first = "password", 
                   second = "confirmPassword",
                   message = "The password fields must match")
public class User  {
 
    @Id
    @GeneratedValue
    @SerializedName("id")
    @Expose
    private long id ;

    @SerializedName("name")
    @NotEmpty(message = "Please provide your name")
    @Expose 
    private String name;

    @SerializedName("email")
    @NotEmpty(message = "Please provide your email")
    @Expose
    @Email
    @Unique(service = UserService.class, fieldName = "email", message = "this email has been taken")
    private String email;

    @SerializedName("password")
    @NotEmpty(message = "Please provide a password")
    @Expose  
    @ValidPassword
    private String password;

    @Transient
    @SerializedName("confirmPassword")
    @NotEmpty(message = "Please Reapet your password")
    private String confirmPassword;

    @SerializedName("confirmation_password")
    @NotEmpty(message = "Please provide your Phone number")
    @Pattern(regexp = "(\\+62|0)[0-9]{9,10}", message = "Your Phone Number Wrong Format")
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @SerializedName("created_at")
    @Expose  
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @SerializedName("updated_at")
    @Expose  
    private Date updatedAt;


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

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getConfrimPassword() {
        return this.confirmPassword;
    }

    public void setConfrimPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}