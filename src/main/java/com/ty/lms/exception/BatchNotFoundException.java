package com.ty.lms.exception;

@SuppressWarnings("serial")
public class BatchNotFoundException extends RuntimeException{
	public BatchNotFoundException(String message) {
		super(message);
	}
}
