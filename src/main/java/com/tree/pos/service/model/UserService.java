package com.tree.pos.service.model;

import java.util.List;

import com.tree.pos.model.User;
import com.tree.pos.validators.intrefaces.CobaSalah;
import com.tree.pos.validators.intrefaces.FieldValueExists;

import org.springframework.stereotype.Component;

@Component
public interface UserService extends FieldValueExists, CobaSalah {
    void save(User user);
    User findByName(String name);
    User findByEmail(String email);
    boolean checkingConfirmPassword(User user);
}