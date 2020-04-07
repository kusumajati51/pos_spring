package com.tree.pos.validators.custom;

import com.tree.pos.model.User;
import com.tree.pos.service.model.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueValidatorUserEmail implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "Duplicate.user.email");
        }
    }


}