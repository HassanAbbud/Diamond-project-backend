package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;


public interface UserRepository extends CrudRepository<User, Long> {
    
} 
