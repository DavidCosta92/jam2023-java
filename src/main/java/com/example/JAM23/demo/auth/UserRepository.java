package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Integer> {

    Optional<User> findByUsername (String username);

    /*


    @Query(value = "    SELECT cursos.id , cursos.description ,cursos.duration, cursos.id_teacher , cursos.name FROM user\n" +
            "    JOIN inscripciones ON user.id = inscripciones.id_user_fk\n" +
            "    JOIN cursos ON cursos.id = inscripciones.id_course_fk\n" +
            "    WHERE user.username = ?;", nativeQuery = true)
    List<Object> findAllCoursesInscripted(String username);
    */
}
