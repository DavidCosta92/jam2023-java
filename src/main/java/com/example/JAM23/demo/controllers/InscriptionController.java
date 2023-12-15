package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import com.example.JAM23.demo.services.CourseService;
import com.example.JAM23.demo.services.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscription")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class InscriptionController {
    private final InscriptionService inscriptionService;

    // get inscripc by id

    // get all students by id course

    @GetMapping("/{id}")
    public ResponseEntity<CourseReadDto> findInscriptionById(@PathVariable Integer idCourse) {
        CourseReadDto course = inscriptionService.findInscriptionById(idCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CourseReadDto>> findAllInscriptions(@PathVariable String username) {
        List<CourseReadDto> coursesInscripted = inscriptionService.findAllCoursesInscriptedByUsername(username);
        return new ResponseEntity<>(coursesInscripted, HttpStatus.OK);
    }
}
