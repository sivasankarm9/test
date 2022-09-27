package com.cg.sms.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DepartmentNotFoundException extends RuntimeException{

	private String message;
}
