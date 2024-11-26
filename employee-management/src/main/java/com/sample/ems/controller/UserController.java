package com.sample.ems.controller;

import com.sample.ems.entity.User;
import com.sample.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public List<User> saveUsers(@RequestBody List<User> users){
        return userRepository.saveAll(users);
    }
}
