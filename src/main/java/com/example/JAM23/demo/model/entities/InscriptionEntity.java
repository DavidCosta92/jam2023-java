package com.example.JAM23.demo.model.entities;

import com.example.JAM23.demo.auth.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity(name="inscription")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_insc;
    @ManyToOne()
    @JoinColumn(name = "id_user_fk" , nullable = false)
    private User user; // No hacen falta agregarlos aca, porque jpa lo agrega en al relacion entre curso y usuario

    @ManyToOne()
    @JoinColumn(name = "id_course_fk" , nullable = false)
    private CourseEntity course;
}
