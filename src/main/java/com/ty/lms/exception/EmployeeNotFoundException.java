package com.ty.lms.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException{
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
