package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
}
