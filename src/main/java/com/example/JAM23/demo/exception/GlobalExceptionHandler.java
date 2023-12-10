package com.example.JAM23.demo.exception;

import com.example.JAM23.demo.exception.customsExceptions.AlreadyExistException;
import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    // excepts personalizadas
    // excepts personalizadas
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ExceptionMessages> alreadyExistException (RuntimeException ex){
        return new ResponseEntity<ExceptionMessages>(new ExceptionMessages(ex.getMessage(), InternalExceptionCodes.DUPLICATE_VALUES.ordinal()) , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessages> notFoundException (RuntimeException ex){
        return new ResponseEntity<ExceptionMessages>(new ExceptionMessages(ex.getMessage(), InternalExceptionCodes.NOT_FOUND_BY_ID.ordinal()) , HttpStatus.NOT_FOUND);
    }



    // excepts defecto
    // excepts defecto
    // excepts defecto
    @ExceptionHandler(IllegalArgumentException.class)
    // @ResponseBody
    public ResponseEntity<ExceptionMessages> handlerArgException (IllegalArgumentException ex){
        return new ResponseEntity<ExceptionMessages>(new ExceptionMessages(ex.getMessage(), InternalExceptionCodes.ILLEGAL_ARGS.ordinal()) , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionMessages> runtimeException (RuntimeException ex){
        return new ResponseEntity<ExceptionMessages>(new ExceptionMessages(ex.getMessage(), InternalExceptionCodes.RUNTIME_EXCEPTIONS.ordinal()) , HttpStatus.BAD_REQUEST);
    }

}
