package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/users")
public class UserController {  

    //Validates User from request body against validators in User entity
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        //TODO: REGISTRATION PROCESS
        return null;
    }


    

}
