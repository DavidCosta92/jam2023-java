package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Integer> {

    Optional<User> findByUsername (String username);
}
