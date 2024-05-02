package com.hnguyen387.jpa_query.specification.globalexception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.query.sqm.PathElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hnguyen387.jpa_query.criterialAPI.globalexceptions.CannotDeleteException;

@ControllerAdvice
public class GlobalHandleException {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException exception) {
		return handleException(exception, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PathElementException.class)
	public ResponseEntity<ErrorDetails> handlePathElementException(PathElementException exception) {
		return handleException(exception, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException exception) {
		return handleException(exception, HttpStatus.EXPECTATION_FAILED);
	}
	@ExceptionHandler(CannotDeleteException.class)
	public ResponseEntity<ErrorDetails> handleCannotDeleteException(CannotDeleteException exception) {
		return handleException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	private ResponseEntity<ErrorDetails> handleException(RuntimeException exception, HttpStatus status) {
		ErrorDetails error = new ErrorDetails();
		error.setStatusCode(status.value());
		error.setStatus(status.name());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		error.setTimeStamp(formatter.format(now));
		error.setErrMessage(exception.getMessage());
		return new ResponseEntity<ErrorDetails>(error, status);
	}
}
