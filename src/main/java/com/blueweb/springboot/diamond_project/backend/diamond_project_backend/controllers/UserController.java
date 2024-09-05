package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.controllers;

import static com.blueweb.springboot.diamond_project.backend.diamond_project_backend.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.blueweb.springboot.diamond_project.backend.diamond_project_backend.security.TokenJwtConfig.PREFIX_TOKEN;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.dto.LogInResponse;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.security.CustomUserDetails;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



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
    
   @GetMapping("/check-token")
    public ResponseEntity<LogInResponse> getToken(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // Retrieve the token from HTTP headers
        String token = request.getHeader(HEADER_AUTHORIZATION);
        String usuario = userDetails.getUsername();

        if (token != null && token.startsWith(PREFIX_TOKEN)) {
            token = token.replace(PREFIX_TOKEN, "");
            LogInResponse response = new LogInResponse(usuario, token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
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

    //FOR TESTING(setting encrypted password)
    @PutMapping("/secure-password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id) {
        Optional<User> optionalUser = userService.updatePassword(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalUser.orElseThrow());        
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping()
    public void deleteUser(Long id){
        userService.delete(id);
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
}
