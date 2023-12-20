package com.example.JAM23.demo.model.dtos.inscriptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionAddDto {
    private Integer id_user;
    private Integer id_course;
}
