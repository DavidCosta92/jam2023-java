package com.example.JAM23.demo.services;

import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.AttendanceMapper;
import com.example.JAM23.demo.model.dtos.attendances.AttendanceDto;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.entities.AttendanceEntity;
import com.example.JAM23.demo.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private AttendanceRepository attendanceRepository;


    public AttendanceDto setAttendance (AttendanceDto attendanceDto){
        // si viene sin ID, asumimos que es un nuevo registro, se pone presente true
        if(attendanceDto.getId_att() == null){
            attendanceDto.setPresent(true);
            AttendanceEntity attendanceEntity = attendanceMapper.attendanceDtoToAttendanceEntity(attendanceDto);
            attendanceRepository.save(attendanceEntity);
        } else{
            // si viene con ID, y en BD es false, lo cambiamos a true o viceversa
            Optional <AttendanceEntity> attendanceBD = attendanceRepository.findById(attendanceDto.getId_att());
            if (attendanceBD.isPresent()){
                boolean newStatus = attendanceBD.get().isPresent() == true? false : true;
                attendanceBD.get().setPresent(newStatus);
                attendanceRepository.save(attendanceBD.get());
            } else {
                // si viene con ID, pero no se encuetra error not found
                // TODO tirar error
                throw new NotFoundException("ID de asistencia no encontrado");
            }
        }
        AttendanceEntity attendance = attendanceRepository.findById(attendanceDto.getId_att()).get();
        return attendanceMapper.attendanceEntityToAttendanceDto(attendance);
    }
    public List<AttendanceDto> getListAttendanceUserByIds (Integer idCourse, Integer idUser){
        // TODO BUSCAR TODAS LAS ASITENCIAS DEL USUARIO A DETERMINADO CURSO
        // TODO BUSCAR TODAS LAS ASITENCIAS DEL USUARIO A DETERMINADO CURSO
        // TODO BUSCAR TODAS LAS ASITENCIAS DEL USUARIO A DETERMINADO CURSO
        // TODO BUSCAR TODAS LAS ASITENCIAS DEL USUARIO A DETERMINADO CURSO
        // TODO BUSCAR TODAS LAS ASITENCIAS DEL USUARIO A DETERMINADO CURSO
        // TODO BUSCAR TODAS LAS ASITENCIAS DEL USUARIO A DETERMINADO CURSO
        // TODO BUSCAR TODAS LAS ASITENCIAS DEL USUARIO A DETERMINADO CURSO
        return new ArrayList<AttendanceDto>();
    }

    // TODO ELIMINAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO VER TODAS LAS ASISTENCIAS DE UN CURSO
    // TODO VER TODAS LAS ASISTENCIAS DE UN USUARIO A DISTINTOS CURSOS?

}
