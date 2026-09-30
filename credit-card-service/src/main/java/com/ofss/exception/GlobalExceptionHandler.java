package com.ofss.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CreditCardNotFoundException.class)
	public ResponseEntity<Map<String, Object>> notFound(CreditCardNotFoundException e) {
		return error(HttpStatus.NOT_FOUND, "Credit Card Not Found", e.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> badRequest(IllegalArgumentException e) {
		return error(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException e) {
		Map<String, Object> body = base(HttpStatus.BAD_REQUEST, "Validation Failed");
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(x -> errors.put(x.getField(), x.getDefaultMessage()));
		body.put("errors", errors);
		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> other(Exception e) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e.getMessage());
	}

	private ResponseEntity<Map<String, Object>> error(HttpStatus s, String error, String message) {
		Map<String, Object> body = base(s, error);
		body.put("message", message);
		return ResponseEntity.status(s).body(body);
	}

	private Map<String, Object> base(HttpStatus s, String error) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", s.value());
		body.put("error", error);
		return body;
	}
}
