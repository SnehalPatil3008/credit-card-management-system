package com.ofss.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MerchantNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(MerchantNotFoundException exception) { return error(HttpStatus.NOT_FOUND, "Merchant Not Found", exception.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, Object> body = base(HttpStatus.BAD_REQUEST, "Validation Failed"); Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage())); body.put("errors", errors); return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOther(Exception exception) { return error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", exception.getMessage()); }
    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String error, String message) { Map<String, Object> body = base(status, error); body.put("message", message); return ResponseEntity.status(status).body(body); }
    private Map<String, Object> base(HttpStatus status, String error) { Map<String, Object> body = new HashMap<>(); body.put("timestamp", LocalDateTime.now()); body.put("status", status.value()); body.put("error", error); return body; }
}
