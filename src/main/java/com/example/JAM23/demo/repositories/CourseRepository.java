package com.example.JAM23.demo.repositories;

import com.example.JAM23.demo.model.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    @Query(value = "    SELECT cursos.id , cursos.description ,cursos.duration, cursos.id_teacher , cursos.name FROM user\n" +
            "    JOIN inscripciones ON user.id = inscripciones.id_user_fk\n" +
            "    JOIN cursos ON cursos.id = inscripciones.id_course_fk\n" +
            "    WHERE user.username = ?;", nativeQuery = true)
    List<CourseEntity> findAllCoursesInscripted(String username);
}
