package com.sample.ems.config;

import com.sample.ems.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DbConfig {

    @Bean
    //@Scope(value = "prototype")
    public User getEmpPojo(){
        return User.builder()
                .id("9889898")
                .name("Test User")
                .build();
    }
}
