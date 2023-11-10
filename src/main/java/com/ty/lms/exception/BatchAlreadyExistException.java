package com.ty.lms.exception;

@SuppressWarnings("serial")
public class BatchAlreadyExistException extends RuntimeException{
	public BatchAlreadyExistException(String message) {
		super(message);
	}
}