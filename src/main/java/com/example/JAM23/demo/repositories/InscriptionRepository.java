package com.example.JAM23.demo.repositories;

import com.example.JAM23.demo.model.entities.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionEntity , Integer> {

}
