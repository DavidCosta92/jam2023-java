package com.example.JAM23.demo.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // private Integer id_user; No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario
    // private Integer id_course; No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario
   //  private String role; esto lo saque porque me parece redundante, ya que todos los inscriptos seran si o si estudiantes
}