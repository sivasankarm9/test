package com.sample.ems.service;

import com.sample.ems.entity.User;

public interface TokenGeneratorService {
    String generateJWT(User user);
}
