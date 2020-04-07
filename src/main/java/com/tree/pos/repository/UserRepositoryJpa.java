package com.tree.pos.repository;

import com.tree.pos.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long>{
    User findByName(String name);
    User findByEmail(String email);

}           