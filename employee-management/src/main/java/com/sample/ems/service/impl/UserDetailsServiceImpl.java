package com.sample.ems.service.impl;

import com.sample.ems.entity.User;
import com.sample.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDetails userDetails = null;
        Optional<User> userOpt =  userRepository.findByName(username);
        if(userOpt.isPresent()){
            User user = userOpt.get();
           return new org.springframework.security.core.userdetails.User(user.getName(), passwordEncoder.encode(user.getPassword()), new ArrayList<>());
        }else {
            throw new RuntimeException("User Not Found.");
        }
    }
}
