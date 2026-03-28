package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidLoanAmountException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAmount(InvalidLoanAmountException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("InvalidLoanAmountException", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DuplicateLoanApplicationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateLoanApplicationException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("DuplicateLoanApplicationException", ex.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(LoanNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("LoanNotFoundException", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Exception", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}