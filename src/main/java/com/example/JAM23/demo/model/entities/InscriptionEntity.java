package com.example.JAM23.demo.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@Entity(name="inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_insc;

    @Column(name = "ID usuario")
    private Integer id_user_fk; // No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario

    @Column(name = "ID curso")
    private Integer id_course_fk; // No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario
   //  private String role; esto lo saque porque me parece redundante, ya que todos los inscriptos seran si o si estudiantes

    // @OneToMany(mappedBy = "inscription")
    // private List<AttendanceEntity> attendanceList;
    @OneToMany(mappedBy = "inscription")
    private List<AttendanceEntity> attendanceList;

}