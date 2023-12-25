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

    // VALIDACION DATOS PUROS
    // TODO STRING NO VACIOS
    public String emptyString(String field, String value){
        if(value.isEmpty()) throw new InvalidValueException(field + " no puede estar vacio!");
        return value;
    }
    // TODO string largo
    public String stringMinSize(String field, Integer minSize, String value){
        if(value.length() < minSize) throw new InvalidValueException(field + " debe tener al menos "+minSize+" caracteres!");
        return value;
    }

    // TODO NO SIMBOLOS Y NO NUMEROS
    public String stringOnlyLettersAndNumbers (String field, String value){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        // "[^a-zA-Z0-9]" => CUALQUIER CARACTER MENOS LOS ENUMERADOS, osea si encuentra un caracter distinto, lanza excepcion
        if(pattern.matcher(value).find()) throw new InvalidValueException(field + " solo puede contener numeros y letras!");
        return value;
    }
    public String stringOnlyLetters (String field, String value){
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        // "[^a-zA-Z]" => CUALQUIER CARACTER MENOS LOS ENUMERADOS, osea si encuentra un caracter distinto, lanza excepcion
        if(pattern.matcher(value).find()) throw new InvalidValueException(field + " solo puede contener letras!");
        return value;
    }
    public String stringOnlyNumbers (String field, String value){
        Pattern pattern = Pattern.compile("[^0-9]");
        if(pattern.matcher(value).find()) throw new InvalidValueException(field + " solo puede contener numeros!");
        return value;
    }

    // VALIDACION DATOS SEGUN NEGOCIO

    // TODO GENDER DEBERIA SER UN ENUM ??

    public String validateUsername(String username){
        stringMinSize("Username", 3 , username);
        return stringOnlyLettersAndNumbers("Username" , username);
    }
    public String validatePassword(String password){
        return stringMinSize("Password", 8 , password);
    }
    public String validateFirstName(String firstName){
        return stringOnlyLetters("FirstName" , firstName);
    }
    public String validateLastName(String lastName){
        return stringOnlyLetters("LastName" , lastName);
    }
    public String validatePhone(String phone){
        return stringOnlyNumbers("Phone" , phone);
    }
    public String validateDni(String dni){
        stringMinSize("Dni",7 , dni);
        return stringOnlyNumbers("Dni" , dni);
    }
    public String validateEmail(String email){
        String regex = "^[a-zA-Z0-9._]+@[a-zA-Z0-9._]+\\.[a-zA-Z]{2,6}$";
        //  antes del "@" => puede tener números, letras, puntos, y guion bajo.
        //  tiene que tener un "@"
        //  Después del "@" puede tener números, letras, puntos y guion bajo.
        //  Finalmente, el dominio debe terminar con un punto seguido de dos a seis letras.
        if(! Pattern.compile(regex).matcher(email).matches() ) throw new InvalidValueException("Email con estructura incorrecta!");
        return email;
    }

    // VALIDACION OBJETOS NEGOCIO

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
