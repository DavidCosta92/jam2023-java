package com.example.JAM23.demo.model.entities;

import com.example.JAM23.demo.auth.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Builder
@Entity(name="inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_insc;

/*
    @Column(name = "id_user_fk")
    private Integer id_user_fk; // No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario

    @Column(name = "id_course_fk")
    private Integer id_course_fk; // No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario
   //  private String role; esto lo saque porque me parece redundante, ya que todos los inscriptos seran si o si estudiantes
*/
    // @OneToMany(mappedBy = "inscription")
    // private List<AttendanceEntity> attendanceList;

    // @OneToMany(mappedBy="inscription")
    // private Map<String, AttendanceEntity> attendanceMap;


    @ManyToOne()
    @JoinColumn(name = "id_usuario_fk" , nullable = false)
    private User user; // No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario

    @ManyToOne()
    @JoinColumn(name = "id_course_fk" , nullable = false)
    private CourseEntity course;
}
