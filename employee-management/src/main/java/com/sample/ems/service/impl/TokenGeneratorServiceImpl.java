package com.sample.ems.service.impl;

import com.sample.ems.entity.User;
import com.sample.ems.service.TokenGeneratorService;
import com.sample.ems.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String generateJWT(User user) {
        String jwt;
        UserDetails userDetails =  userDetailsService.loadUserByUsername(user.getName());
        return jwtUtil.generateToken(userDetails);
    }
}
