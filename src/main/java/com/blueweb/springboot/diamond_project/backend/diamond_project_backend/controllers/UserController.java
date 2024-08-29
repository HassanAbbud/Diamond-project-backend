package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/users")
public class UserController {  

    @Autowired
    private UserService userService;
    
    @GetMapping()
    public List<User> listUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    
    //Validates User from request body against validators in User entity
    //TODO: Have to set Identity Specification property to YES in SQL Server
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
