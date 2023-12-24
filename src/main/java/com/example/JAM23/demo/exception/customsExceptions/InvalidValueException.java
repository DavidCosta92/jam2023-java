package com.example.JAM23.demo.exception.customsExceptions;

public class InvalidValueException extends RuntimeException{
    public InvalidValueException(String message) {
        super(message);
    }
}
