package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Integer> {
    Optional<User> findByUsername (String username);

    @Query(value = "SELECT user.id, user.dni, user.email, user.first_name,user.password, user.last_name, user.gender, user.phone, user.role, user.username FROM user\n" +
            "JOIN inscription ON user.id = inscription.id_user_fk\n" +
            "WHERE inscription.id_course_fk =  ?;", nativeQuery = true)
    List<User> findAllInscriptedUsersIdByIdCourse(Integer idCourse);
}
