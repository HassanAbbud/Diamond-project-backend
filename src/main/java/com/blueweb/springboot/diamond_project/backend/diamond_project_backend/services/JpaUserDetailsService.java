package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;


import java.util.Optional;
import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsuario(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s doesn't exist in the system", username));
        }

        User user = userOptional.orElseThrow();

        return new org.springframework.security.core.userdetails.User(
            user.getUsuario(), 
            user.getPass(), 
            true,  // enabled
            true,  // accountNonExpired
            true,  // credentialsNonExpired
            true,  // accountNonLocked
            new ArrayList<>()  // authorities
         );
    }

}
