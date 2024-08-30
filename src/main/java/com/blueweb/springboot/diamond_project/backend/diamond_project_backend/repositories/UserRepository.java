package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;


public interface UserRepository extends CrudRepository<User, Long> {
    //JPA for finding by usernames 
    Optional<User> findByUsuario(String username);
} 
