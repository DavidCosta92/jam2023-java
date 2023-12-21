package com.example.JAM23.demo.repositories;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionEntity , Integer> {
    @Query(value = "SELECT i.id_insc , i.id_course_fk , i.id_user_fk FROM inscription i WHERE i.id_course_fk = ?1 AND i.id_user_fk = ?2", nativeQuery = true)
    InscriptionEntity findByIdsCourseAndUser (Integer id_course_fk, Integer id_user_fk); // findById_course_fkAndId_user_fk
}
