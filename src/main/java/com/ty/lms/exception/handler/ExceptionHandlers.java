package com.ty.lms.exception.handler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.lms.exception.BatchAlreadyExistException;
import com.ty.lms.exception.BatchNotFoundException;
import com.ty.lms.exception.EmployeeAlreadyExistException;
import com.ty.lms.exception.EmployeeNotFoundException;
import com.ty.lms.exception.MentorNotFoundException;
import com.ty.lms.exception.MockNotAttendedException;
import com.ty.lms.exception.RequestNotApprovedException;
import com.ty.lms.exception.RoleNotFoundException;
import com.ty.lms.response.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(value = { RoleNotFoundException.class, EmployeeNotFoundException.class,
			UsernameNotFoundException.class, BatchNotFoundException.class, MentorNotFoundException.class,
			MockNotAttendedException.class })
	public ResponseEntity<ErrorResponse> handler(RuntimeException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().error(exception.getMessage()).timestamp(LocalDateTime.now()).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { EmployeeAlreadyExistException.class, BatchAlreadyExistException.class })
	public ResponseEntity<ErrorResponse> handler_(RuntimeException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().error(exception.getMessage()).timestamp(LocalDateTime.now()).build(),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { SQLIntegrityConstraintViolationException.class })
	public ResponseEntity<ErrorResponse> handler_(SQLIntegrityConstraintViolationException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().error(exception.getMessage()).timestamp(LocalDateTime.now()).build(),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { RequestNotApprovedException.class })
	public ResponseEntity<ErrorResponse> handler_(RequestNotApprovedException exception) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().error(exception.getMessage()).timestamp(LocalDateTime.now()).build(),
				HttpStatus.UNAUTHORIZED);
	}
}
