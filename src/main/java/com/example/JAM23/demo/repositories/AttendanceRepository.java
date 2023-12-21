package com.example.JAM23.demo.repositories;

import com.example.JAM23.demo.model.entities.AttendanceEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {

    @Query(value = "SELECT att.id_att , att.date, att.present, att.inscription_id_insc FROM attendance att WHERE att.inscription_id_insc = ?;", nativeQuery = true)
    List<AttendanceEntity> findAttendanceListByIdInsc (Integer idInsc); // findByInscription_id_insc
}
