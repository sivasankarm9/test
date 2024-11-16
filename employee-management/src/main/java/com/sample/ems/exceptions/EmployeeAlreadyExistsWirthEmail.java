package com.sample.ems.exceptions;

public class EmployeeAlreadyExistsWirthEmail extends RuntimeException{

    public EmployeeAlreadyExistsWirthEmail(String message){
        super(message);
    }
}
