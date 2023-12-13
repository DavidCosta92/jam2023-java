package com.example.JAM23.demo.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoguedUserDetails {
    String token;
    Integer id;
    String username;
    String lastName;
    String firstName;
    String phone;
    String dni;
    String email;
    String gender;
}
