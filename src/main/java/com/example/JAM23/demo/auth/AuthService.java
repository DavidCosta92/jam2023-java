package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.Role;
import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.entities.AuthResponse;
import com.example.JAM23.demo.auth.entities.LoginRequest;
import com.example.JAM23.demo.auth.entities.LoguedUserDetails;
import com.example.JAM23.demo.auth.entities.RegisterRequest;
import com.example.JAM23.demo.exception.customsExceptions.AlreadyExistException;
import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.jwt.JwtAuthenticationFilter;
import com.example.JAM23.demo.jwt.JwtService;
import com.example.JAM23.demo.utils.Validator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    Validator validator;

// TODO => AGREGAR ENDPOINT Y LOGICA PARA BLOQUEAR USUARIOS... BUSCAR METODO isAccountNonLocked(), AGREGAR ENDPOINT Y LOGICA PARA
//  MANEJAR USUARIOS BLOQUEADOS, Deberiamos ver que es mejor, tal vez, la forma mas eficiente,
//  es por roles? tipo rol BLOQUED_USER?

// TODO =>

    public AuthResponse login(LoginRequest request) {
        //Authentica al usuario, osea lo guarda el contex security holder, dentro de un obj que representa el usuario logueado
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword()));

        UserDetails userDetails = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(()->new NotFoundException(("User not found")));
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        String req_username = request.getUsername();
        String req_password = request.getUsername();
        String req_firstName = request.getFirstName();
        String req_lastName = request.getLastName();
        String req_phone = request.getPhone();
        String req_dni = request.getDni();
        String req_email = request.getEmail();
        String req_gender = request.getGender();


        validator.alreadyExistUser(req_username, req_dni , req_email); // TROWS EXCEPTS

        User user = new User().builder()
                        .username(req_username)
                        .password(passwordEncoder.encode(req_password))
                        .firstName(req_firstName)
                        .lastName(req_lastName)
                        .phone(req_phone)
                        .dni(req_dni)
                        .email(req_email)
                        .gender(req_gender)
                        .role(Role.USER)
                        .build();
        userRepository.save(user);

        //Authentica al usuario, osea lo guarda el contex security holder, dentro de un obj que representa el usuario logueado
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword()));
        return AuthResponse.builder()
                        .token(jwtService.getToken(user))
                        .build();
    }

    public LoguedUserDetails getLoguedUserDetails (HttpHeaders headers){
        String token = jwtService.getTokenFromHeader(headers);
        String username = jwtService.getUsernameFromToken(token);
        User loguedUser = userRepository.findByUsername(username).get();

        return new LoguedUserDetails()
                .builder()
                .token(token)
                .id(loguedUser.getId())
                .username(username)
                .firstName(loguedUser.getFirstName())
                .lastName(loguedUser.getLastName())
                .phone(loguedUser.getPhone())
                .dni(loguedUser.getDni())
                .email((loguedUser.getEmail()))
                .gender(loguedUser.getGender())
                .build();
    }
}
