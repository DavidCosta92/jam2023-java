package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionAddDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import com.example.JAM23.demo.model.entities.AttendanceEntity;
import com.example.JAM23.demo.services.InscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscription/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class InscriptionController {
    private final InscriptionService inscriptionService;

    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS


    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<InscriptionReadDto>> findAllInscription() {
        return new ResponseEntity<>(inscriptionService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @PostMapping()
    public ResponseEntity<InscriptionReadDto> createInscription (@RequestBody InscriptionAddDto inscriptionAddDto){
        return new ResponseEntity<>(inscriptionService.createInscription(inscriptionAddDto) , HttpStatus.OK);
    }

    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @GetMapping("{idInscription}")
    public ResponseEntity<InscriptionReadDto> findInscriptionById(@PathVariable Integer idInscription) {        ;
        return new ResponseEntity<>(inscriptionService.findInscriptionById(idInscription), HttpStatus.OK);
    }

    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @PutMapping("{idInscription}")
    public ResponseEntity<InscriptionReadDto> editInscriptionById(@PathVariable Integer idInscription,
                                                                  @RequestBody InscriptionAddDto inscriptionAddDto){
        return new ResponseEntity<>(inscriptionService.editInscriptionById(idInscription , inscriptionAddDto), HttpStatus.ACCEPTED);
    }

    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @DeleteMapping("{idInscription}")
    public ResponseEntity<String> deleteInscriptionById(@PathVariable Integer idInscription) {
        inscriptionService.deleteInscriptionById(idInscription);
        return new ResponseEntity<>("Inscripcion eliminada!", HttpStatus.ACCEPTED);
    }

    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @GetMapping("user/{username}")
    public ResponseEntity<List<CourseReadDto>> findAllInscriptions(@PathVariable String username) {
        return new ResponseEntity<>(inscriptionService.findAllCoursesInscriptedByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = ">>>>> PENDIENTE <<<<<")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "===== PENDIENTE =====",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "===== PENDIENTE =====",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "===== PENDIENTE =====",
                    content = @Content) })
    @GetMapping("course/{idCourse}")
    public ResponseEntity<List<UserReadDto>> findAllInscriptedUsersByIdCourse(@PathVariable Integer idCourse) {
        return new ResponseEntity<>(inscriptionService.findAllInscriptedUsersByIdCourse(idCourse), HttpStatus.OK);
    }


}
