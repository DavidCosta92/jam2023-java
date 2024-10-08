package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.Role;
import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.entities.AuthResponse;
import com.example.JAM23.demo.auth.entities.LoginRequest;
import com.example.JAM23.demo.auth.entities.LoguedUserDetails;
import com.example.JAM23.demo.auth.entities.RegisterRequest;
import com.example.JAM23.demo.exception.customsExceptions.AlreadyExistException;
import com.example.JAM23.demo.exception.customsExceptions.InvalidJwtException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Role createRoleByEmail ( String email){
        Role role = null;
        switch (email){
            case "admin@gmail.com":
                role = Role.ADMIN;
                break;
            case "profe@gmail.com":
                role = Role.TEACHER;
                break;
            default:
                role = Role.USER;
        }
        return role;
    }
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
        // validations, return value or trows exceptions
        String req_username = validator.validateUsername(request.getUsername());
        String req_password = validator.validatePassword(request.getPassword());
        String req_firstName = validator.validateFirstName(request.getFirstName());
        String req_lastName = validator.validateLastName(request.getLastName());
        String req_phone = validator.validatePhone(request.getPhone());
        String req_dni = validator.validateDni(request.getDni());
        String req_email = validator.validateEmail(request.getEmail());

        validator.alreadyExistUser(req_username, req_dni , req_email); // TROWS EXCEPTS

        // TODO GENDER DEBERIA SER UN ENUM ??
        String req_gender = request.getGender();


        User user = new User().builder()
                        .username(req_username)
                        .password(passwordEncoder.encode(req_password))
                        .firstName(req_firstName)
                        .lastName(req_lastName)
                        .phone(req_phone)
                        .dni(req_dni)
                        .email(req_email)
                        .gender(req_gender)
                        .role(createRoleByEmail(req_email))
                        .build();
        userRepository.save(user);

        //Authentica al usuario, osea lo guarda el contex security holder, dentro de un obj que representa el usuario logueado
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername() , request.getPassword()));
        return AuthResponse.builder()
                        .token(jwtService.getToken(user))
                        .build();
    }
    public LoguedUserDetails getLoguedUserDetails (HttpHeaders headers){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User loguedUser = (User) securityContext.getAuthentication().getPrincipal();

        return new LoguedUserDetails()
                .builder()
                .id(loguedUser.getId())
                .username(loguedUser.getUsername())
                .firstName(loguedUser.getFirstName())
                .lastName(loguedUser.getLastName())
                .phone(loguedUser.getPhone())
                .dni(loguedUser.getDni())
                .email((loguedUser.getEmail()))
                .gender(loguedUser.getGender())
                .role(loguedUser.getRole())
                .authorities(loguedUser.getAuthorities())
                .build();
    }
}
