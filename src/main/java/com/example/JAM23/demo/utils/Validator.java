package com.example.JAM23.demo.utils;


import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.UserRepository;
import com.example.JAM23.demo.exception.customsExceptions.AlreadyExistException;
import com.example.JAM23.demo.exception.customsExceptions.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class Validator {
    @Autowired
    UserRepository userRepository;


    // VALIDACION DATOS
    // TODO STRING NO VACIOS
    public void emptyString(String field, String value){
        if(value.isEmpty()) throw new InvalidValueException(field + " no puede estar vacio!");
    }
    // TODO string largo
    public void stringMinSize(String field, Integer minSize, String value){
        if(value.length() < minSize) throw new InvalidValueException(field + " debe tener al menos "+minSize+" caracteres!");
    }

    // TODO NO SIMBOLOS Y NO NUMEROS
    public void stringOnlyLettersAndNumbers (String field, String value){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
        if(!pattern.matcher(value).find()) throw new InvalidValueException(field + " solo puede contener numeros y letras!");
    }
    public void stringOnlyLetters (String field, String value){
        Pattern pattern = Pattern.compile("[a-zA-Z]+"); // ("[^a-zA-Z]");
        if(!pattern.matcher(value).find()) throw new InvalidValueException(field + " solo puede contener letras!");
    }
    public void stringOnlyNumbers (String field, String value){
        Pattern pattern = Pattern.compile("[0-9]+"); //("[^0-9]");
        if(!pattern.matcher(value).find()) throw new InvalidValueException(field + " solo puede contener numeros!");
    }



    // TODO validar email QUE CONTENGA @

    // TODO USERNAME NO PUEDE CONTENER SIMBOLOS? SOLO NUMEROS Y LETRAS?

    // TODO PHONE QUE TENGA UN VALOR NUMERICO, Y QUE TENGA TAL CANTIDAD DE CARACTERES?
    // TODO DNI QUE TENGA UN VALOR NUMERICO, Y QUE TENGA TAL CANTIDAD DE CARACTERES?
    // TODO GENDER DEBERIA SER UN ENUM ??


    // VALIDACION OBJETOS

    public boolean existUserByUsername( String username){
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.isPresent();
    }
    public boolean existUserByDni( String dni){
        Optional<User> byDni = userRepository.findByDni(dni);
        return byDni.isPresent();
    }
    public boolean existUserByEmail( String email){
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }

    public void alreadyExistUser(String username, String dni, String email){
        if(!existUserByUsername(username) || !existUserByDni( dni) || !existUserByEmail( email)){
            throw new AlreadyExistException("Datos ya existentes, revisa los campos!");
        }
    }
}
