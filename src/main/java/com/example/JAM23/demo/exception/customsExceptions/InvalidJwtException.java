package com.example.JAM23.demo.exception.customsExceptions;

public class InvalidJwtException extends RuntimeException{
    public InvalidJwtException(String msg){super(msg);}
}