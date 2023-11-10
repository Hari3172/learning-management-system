package com.ty.lms.exception;

@SuppressWarnings("serial")
public class EmployeeAlreadyExistException extends RuntimeException{
	public EmployeeAlreadyExistException(String message) {
		super(message);
	}
}