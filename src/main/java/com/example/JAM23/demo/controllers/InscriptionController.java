package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionAddDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
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

    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS

    @PutMapping("/{idInscription}")
    public ResponseEntity<InscriptionReadDto> editInscriptionById(@PathVariable Integer idInscription,
                                                                  @RequestBody InscriptionAddDto inscriptionAddDto){
        return new ResponseEntity<>(inscriptionService.editInscriptionById(idInscription , inscriptionAddDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<InscriptionReadDto>> findAllInscription() {
        return new ResponseEntity<>(inscriptionService.findAll(), HttpStatus.OK);
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

    @GetMapping("/course/{idCourse}")
    public ResponseEntity<List<UserReadDto>> findAllInscriptedUsersByIdCourse(@PathVariable Integer idCourse) {
        List<UserReadDto> inscriptedUsers = inscriptionService.findAllInscriptedUsersByIdCourse(idCourse);
        return new ResponseEntity<>(inscriptedUsers, HttpStatus.OK);
    }

    @DeleteMapping("/{idInscription}")
    public ResponseEntity<String> deleteInscriptionById(@PathVariable Integer idInscription) {
        inscriptionService.deleteInscriptionById(idInscription);
        return new ResponseEntity<>("Inscripcion eliminada!", HttpStatus.ACCEPTED);
    }
}
