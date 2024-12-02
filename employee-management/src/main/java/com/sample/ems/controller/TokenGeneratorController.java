package com.sample.ems.controller;

import com.sample.ems.entity.User;
import com.sample.ems.service.TokenGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenGeneratorController {

    @Autowired
    TokenGeneratorService tokenGeneratorService;

    @PostMapping("/auth/token")
    public String generateJWT(@RequestBody User user){
        return tokenGeneratorService.generateJWT(user);
    }
}
