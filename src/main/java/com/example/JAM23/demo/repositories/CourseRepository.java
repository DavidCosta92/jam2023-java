package com.example.JAM23.demo.repositories;

import com.example.JAM23.demo.model.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    @Query(value = "    SELECT course.id , course.description ,course.duration, course.id_teacher , course.name FROM user\n" +
            "    JOIN inscription ON user.id = inscription.id_user_fk\n" +
            "    JOIN course ON course.id = inscription.id_course_fk\n" +
            "    WHERE user.username = ?;", nativeQuery = true)
    List<CourseEntity> findAllCoursesInscripted(String username);
}
