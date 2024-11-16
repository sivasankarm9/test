package com.sample.ems.annotations.validator;

import com.sample.ems.annotations.CompanyEmail;
import com.sample.ems.entity.Employee;
import com.sample.ems.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.regex.Pattern;

public class UniqueMailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Employee> empOpt = employeeRepository.findByEmail(email);
        return empOpt.isPresent() ? false : true;
    }
}
