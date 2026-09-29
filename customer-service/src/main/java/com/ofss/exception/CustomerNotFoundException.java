package com.ofss.exception;

public class CustomerNotFoundException
        extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}