package com.sample.ems.annotations.validator;

import com.sample.ems.annotations.CompanyEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CompanyMailValidator implements ConstraintValidator<CompanyEmail, String> {
    @Override
    public void initialize(CompanyEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        if(pattern.matcher(email).find() && email.contains("hcl.com")){
            return true;
        }
        return false;
    }
}
