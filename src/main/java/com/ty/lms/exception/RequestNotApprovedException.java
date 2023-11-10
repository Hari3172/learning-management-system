package com.ty.lms.exception;

@SuppressWarnings("serial")
public class RequestNotApprovedException extends RuntimeException{
	public RequestNotApprovedException(String message) {
		super(message);
	}
}
