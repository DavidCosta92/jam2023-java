package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.entities.AuthResponse;
import com.example.JAM23.demo.auth.entities.LoginRequest;
import com.example.JAM23.demo.auth.entities.LoguedUserDetails;
import com.example.JAM23.demo.auth.entities.RegisterRequest;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {
    private final AuthService authService;

    @PostMapping ("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping ("register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    @GetMapping ("user")
    public ResponseEntity<LoguedUserDetails> getLoguedUserDetails(@RequestHeader HttpHeaders headers){
        return ResponseEntity.ok(authService.getLoguedUserDetails(headers));
    }
}
