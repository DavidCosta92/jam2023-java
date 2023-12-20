package com.example.JAM23.demo.model.dtos.inscriptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionReadDto {
    private Integer id;

    private Integer id_user;
    private String username;
    private String email;
    private String lastName;
    private String firstName;

    private Integer id_course;
    private Integer id_teacher;
    private String courseName;

}
