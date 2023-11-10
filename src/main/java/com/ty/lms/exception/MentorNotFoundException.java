package com.ty.lms.exception;

@SuppressWarnings("serial")
public class MentorNotFoundException extends RuntimeException {
	public MentorNotFoundException(String message) {
		super(message);
	}
}
