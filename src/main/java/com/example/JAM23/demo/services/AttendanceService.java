package com.example.JAM23.demo.services;

import com.example.JAM23.demo.mappers.AttendanceMapper;
import com.example.JAM23.demo.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private AttendanceRepository attendanceRepository;


    // TODO ASISTENCIA A TAL CURSO, ID USER E ID CURSO

    // TODO DAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO ELIMINAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO VER TODAS LAS ASISTENCIAS DE UN CURSO
    // TODO VER TODAS LAS ASISTENCIAS DE UN USUARIO A DISTINTOS CURSOS?

}
