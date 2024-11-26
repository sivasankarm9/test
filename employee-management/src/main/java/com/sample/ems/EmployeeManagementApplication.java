package com.sample.ems;

import com.sample.ems.entity.User;
import com.sample.ems.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.sample.ems", "com.sample.outside"})
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
