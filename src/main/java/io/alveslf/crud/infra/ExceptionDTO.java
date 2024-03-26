package io.alveslf.crud.infra;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(Integer status, String message, Instant timestamp ) {

	public ExceptionDTO {
		status = status != null ? status : 0;
		message = message != null ? message : "Unexpected error";
		timestamp = timestamp != null ? timestamp : Instant.now();
	}
}
