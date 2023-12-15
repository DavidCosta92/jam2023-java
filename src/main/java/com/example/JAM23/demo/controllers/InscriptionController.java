package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import com.example.JAM23.demo.repositories.InscriptionRepository;
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
    private final InscriptionRepository inscriptionRepository;


    // TODO get all students by id course
    // TODO get all students by id course
    // TODO get all students by id course
    // TODO get all students by id course
    // TODO get all students by id course
    // TODO get all students by id course



    @GetMapping
    public ResponseEntity<List<InscriptionReadDto>> findAllInscription() {
        List<InscriptionReadDto> allInscriptions= inscriptionService.findAll();
        return new ResponseEntity<>(allInscriptions, HttpStatus.OK);
    }

    @GetMapping("/{idInscription}")
    public ResponseEntity<InscriptionReadDto> findInscriptionById(@PathVariable Integer idInscription) {        ;
        return new ResponseEntity<>(inscriptionService.findInscriptionById(idInscription), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CourseReadDto>> findAllInscriptions(@PathVariable String username) {
        List<CourseReadDto> coursesInscripted = inscriptionService.findAllCoursesInscriptedByUsername(username);
        return new ResponseEntity<>(coursesInscripted, HttpStatus.OK);
    }
}
