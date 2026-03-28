package com.cg.exception;

public class DuplicateLoanApplicationException extends RuntimeException {
    public DuplicateLoanApplicationException(String message) {
        super(message);
    }
}