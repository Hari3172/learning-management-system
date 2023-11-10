package com.ty.lms.exception;

@SuppressWarnings("serial")
public class RoleNotFoundException extends RuntimeException{
	public RoleNotFoundException(String message) {
		super(message);
	}
}
