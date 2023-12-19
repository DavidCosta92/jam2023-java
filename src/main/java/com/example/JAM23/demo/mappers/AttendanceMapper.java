package com.example.JAM23.demo.mappers;

import com.example.JAM23.demo.model.dtos.attendances.AttendanceDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.entities.AttendanceEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import com.example.JAM23.demo.repositories.InscriptionRepository;
import com.example.JAM23.demo.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AttendanceMapper {

    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private InscriptionMapper inscriptionMapper;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public AttendanceEntity attendanceDtoToAttendanceEntity (AttendanceDto attendanceDto){
        InscriptionEntity inscriptionEntity = inscriptionRepository.findById(attendanceDto.getId_inscription()).get();
        return Optional
                .ofNullable(attendanceDto)
                .map(dto ->{
                    return AttendanceEntity.builder()
                            .id_att(attendanceDto.getId_att())
                            .date(attendanceDto.getDate())
                            .present(attendanceDto.isPresent())
                            .inscription(inscriptionEntity)
                            .build(); })
                .orElse(new AttendanceEntity());
    }

    public AttendanceDto attendanceEntityToAttendanceDto(AttendanceEntity attendanceEntity){
        return Optional
                .ofNullable(attendanceEntity)
                .map( entity ->{
                    return AttendanceDto.builder()
                            .id_att(attendanceEntity.getId_att())
                            .date(attendanceEntity.getDate())
                            .present(attendanceEntity.isPresent())
                            .id_inscription(attendanceEntity.getInscription().getId_insc())
                            .build();
                })
                .orElse(new AttendanceDto());

    }
}
