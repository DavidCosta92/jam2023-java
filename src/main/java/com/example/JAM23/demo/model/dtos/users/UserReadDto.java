package com.example.JAM23.demo.model.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDto {
    private Integer id;
    private String username;
    private String lastName;
    private String firstName;
    private String phone;
    private String dni;
    private String email;
    private String gender;
}
