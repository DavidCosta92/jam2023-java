package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.Role;
import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.entities.AuthResponse;
import com.example.JAM23.demo.auth.entities.LoginRequest;
import com.example.JAM23.demo.auth.entities.RegisterRequest;
import com.example.JAM23.demo.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    /*
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;
     */
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
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
