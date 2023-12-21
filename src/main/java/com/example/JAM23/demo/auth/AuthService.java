package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.Role;
import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.entities.AuthResponse;
import com.example.JAM23.demo.auth.entities.LoginRequest;
import com.example.JAM23.demo.auth.entities.LoguedUserDetails;
import com.example.JAM23.demo.auth.entities.RegisterRequest;
import com.example.JAM23.demo.jwt.JwtAuthenticationFilter;
import com.example.JAM23.demo.jwt.JwtService;
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

// TODO => AGREGAR ENDPOINT Y LOGICA PARA BLOQUEAR USUARIOS... BUSCAR METODO isAccountNonLocked(), AGREGAR ENDPOINT Y LOGICA PARA
//  MANEJAR USUARIOS BLOQUEADOS, Deberiamos ver que es mejor, tal vez, la forma mas eficiente,
//  es por roles? tipo rol BLOQUED_USER?

// TODO =>

    public AuthResponse login(LoginRequest request) {
        //Authentica al usuario, osea lo guarda el contex security holder, dentro de un obj que representa el usuario logueado
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword()));

        UserDetails userDetails = userRepository
                .findByUsername(request.getUsername())
                // .orElseThrow(()->new UsernameNotFoundException(("User not found")));
                .orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User().builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .dni(request.getDni())
                .email((request.getEmail()))
                .gender(request.getGender())
                .role(Role.USER)
                .build();

        userRepository.save(user);
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
