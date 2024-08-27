package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;
    
import java.util.List;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;

public interface UserService {
    List<User> findAllUsers();

    User saveUser(User user);
}
