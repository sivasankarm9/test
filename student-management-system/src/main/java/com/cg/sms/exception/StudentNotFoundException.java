package com.cg.sms.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentNotFoundException extends RuntimeException{

	private String message;
}
