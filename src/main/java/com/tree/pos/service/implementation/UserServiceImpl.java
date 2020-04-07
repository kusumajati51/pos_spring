package com.tree.pos.service.implementation;


import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tree.pos.model.User;
import com.tree.pos.repository.UserRepositoryJpa;
import com.tree.pos.service.model.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositoryJpa userRepository;

    JdbcDaoSupport jbdc = new JdbcDaoSupport(){};

    @Override
    @Transactional(rollbackFor = {TransactionSystemException.class})
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
            return userRepository.findByEmail(email);
    }

    @Override
    public boolean fieldValueExist(Object value, String fieldName) throws UnsupportedOperationException {
        if(!fieldName.equals("email")){
            throw new UnsupportedOperationException("");
        }

        if(value == null){
            return false;
        }
        return userRepository.findByEmail(value.toString()) != null;
    }


    
}