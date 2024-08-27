package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {  


    @GetMapping()
    public List<User> listUsers() {
        //TODO: Call service find all users
        return null;
    }
    
    //Validates User from request body against validators in User entity
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user, BindingResult result) {
        //TODO: REGISTRATION PROCESS
        return null;
    }

}
