package com.tree.pos.service.implementation;

import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import com.tree.pos.model.User;
import com.tree.pos.repository.UserRepositoryJpa;
import com.tree.pos.service.model.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    
    @Override
    public boolean match(Object object) {
        User user = (User) object;  
        return user.getPassword()
                   .equals(user.getConfrimPassword());      
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found with email " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), 
                   user.getPassword(), new ArrayList<>());
    }
}