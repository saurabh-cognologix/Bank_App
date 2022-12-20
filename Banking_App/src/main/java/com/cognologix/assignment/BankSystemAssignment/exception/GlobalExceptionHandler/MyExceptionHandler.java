package com.cognologix.assignment.BankSystemAssignment.exception.GlobalExceptionHandler;


import com.cognologix.assignment.BankSystemAssignment.exception.CustomerAlreadyExistException;
import com.cognologix.assignment.BankSystemAssignment.exception.InsufficientBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler({
           // CustomerAlreadyExistException.class,
            InsufficientBalanceException.class,

    })
    public ResponseEntity<String> handleIllegalTypeOfAccountException(Exception exception){
        return new ResponseEntity<>("Exception : "+exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}