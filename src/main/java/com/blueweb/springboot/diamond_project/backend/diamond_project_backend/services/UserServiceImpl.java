package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        user.setPass(passwordEncoder.encode(user.getPass())); //encrypt password
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<User> updatePassword(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userDb = optionalUser.orElseThrow();
            userDb.setPass(passwordEncoder.encode(userDb.getPass()));
            return Optional.of(userRepository.save(userDb));
        }
        return optionalUser;
    }

    @Override
    @Transactional
    public Optional<User> delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(userDb -> userRepository.deleteById(id));
        return optionalUser;
    }

    
}
