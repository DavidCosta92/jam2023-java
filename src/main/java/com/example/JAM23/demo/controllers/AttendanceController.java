package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.model.dtos.attendances.AttendanceDto;
import com.example.JAM23.demo.services.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // TODO VER ASISTENCIA A TAL CURSO, ID USER E ID CURSO

    @GetMapping ("{idCourse}/{idUser}")
    public ResponseEntity<List<AttendanceDto>> getListAttendanceUserByIds(@PathVariable Integer idCourse, @PathVariable Integer idUser){
        return new ResponseEntity<>(attendanceService.getListAttendanceUserByIds(idCourse , idUser), HttpStatus.OK);
    }
    @PostMapping("/setAttendance")
    public ResponseEntity<AttendanceDto> setAttendance (@RequestBody AttendanceDto attendanceDto){
        return new ResponseEntity<>(attendanceService.setAttendance(attendanceDto), HttpStatus.OK);
    }

    // TODO ELIMINAR ASISTENCIA DE TAL USER A TAL CURSO
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO

    // TODO VER TODAS LAS ASISTENCIAS DE UN CURSO
    // TODO VER TODAS LAS ASISTENCIAS DE UN USUARIO A DISTINTOS CURSOS?
}
