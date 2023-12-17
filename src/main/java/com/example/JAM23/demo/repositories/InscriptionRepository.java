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

}
