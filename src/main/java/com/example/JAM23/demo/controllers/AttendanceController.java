package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;



    // TODO ASISTENCIA A TAL CURSO, ID USER E ID CURSO

    // TODO DAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO ELIMINAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO VER TODAS LAS ASISTENCIAS DE UN CURSO
    // TODO VER TODAS LAS ASISTENCIAS DE UN USUARIO A DISTINTOS CURSOS?
}
