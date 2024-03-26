package io.alveslf.crud.infra;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity handle404(EntityNotFoundException e) {
		ExceptionDTO exceptionReturned = new ExceptionDTO(HttpStatus.NOT_FOUND.value(), "Resource not found", Instant.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionReturned);
	}
}
