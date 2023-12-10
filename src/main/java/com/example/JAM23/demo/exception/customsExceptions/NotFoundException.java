package com.example.JAM23.demo.exception.customsExceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String msg){
        super(msg);
    }
}
