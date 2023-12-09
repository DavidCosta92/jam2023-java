package com.example.JAM23.demo.auth;

import com.example.JAM23.demo.auth.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Integer> {

    Optional<User> findByUsername (String username);
}
