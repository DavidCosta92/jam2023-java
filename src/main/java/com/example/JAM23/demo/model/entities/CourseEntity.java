package com.example.JAM23.demo.model.entities;

import com.example.JAM23.demo.auth.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="course")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idTeacher;
    private String name;
    private Integer duration;
    private String description;

    /*
    @ManyToMany(mappedBy = "courses" , targetEntity = User.class)
    private Set<User> users = new HashSet<>();
     */
    @OneToMany(mappedBy = "course")
    private Set<InscriptionEntity> inscription;

}
