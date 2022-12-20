package com.cognologix.assignment.BankSystemAssignment.exception;

public class CustomerAlreadyExistException extends RuntimeException{
    public CustomerAlreadyExistException() {
    }
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
