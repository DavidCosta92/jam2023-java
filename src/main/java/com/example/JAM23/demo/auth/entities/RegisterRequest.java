package com.example.JAM23.demo.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    String phone;
    String dni;
    String email;
    String gender;
}
