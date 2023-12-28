package com.example.JAM23.demo.auth.entities;

import com.example.JAM23.demo.auth.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


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
    Role role;
    Collection<? extends GrantedAuthority> authorities;
}
