package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.exception.ExceptionMessages;
import com.example.JAM23.demo.model.dtos.attendances.AttendanceDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscription/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class InscriptionController {
    private final InscriptionService inscriptionService;

    @Operation(summary = "Shows inscriptions, requires a valid JWT with ADMIN ROL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with all the inscriptions",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InscriptionReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden, Access Denied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<InscriptionReadDto>> findAllInscription() {
        return new ResponseEntity<>(inscriptionService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Creates inscription, requires a valid JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscription success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InscriptionReadDto.class)) }),
            @ApiResponse(responseCode = "406", description = "Not Acceptable, Error as result of sending invalid data, Ex: 'ID usuario incorrecto' ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) ,
            @ApiResponse(responseCode = "409", description = "Conflic, Error as result of sending duplicated inscription",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) })})
    @PostMapping()
    public ResponseEntity<InscriptionReadDto> createInscription (@RequestBody InscriptionAddDto inscriptionAddDto){
        return new ResponseEntity<>(inscriptionService.createInscription(inscriptionAddDto) , HttpStatus.OK);
    }

    @Operation(summary = "Shows inscription by ID, requires a valid JWT with role ADMIN or TEACHER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscription by ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InscriptionReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden, Access Denied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping("{idInscription}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ResponseEntity<InscriptionReadDto> findInscriptionById(@PathVariable Integer idInscription) {        ;
        return new ResponseEntity<>(inscriptionService.findInscriptionById(idInscription), HttpStatus.OK);
    }

    @Operation(summary = "Edit inscription by ID, requires a valid JWT with role ADMIN or TEACHER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edit inscription by ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InscriptionReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden, Access Denied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "406", description = "Not acceptable, Error as result of sending invalid data, Ex: 'ID curso incorrecto' ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @PutMapping("{idInscription}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ResponseEntity<InscriptionReadDto> editInscriptionById(@PathVariable Integer idInscription,
                                                                  @RequestBody InscriptionAddDto inscriptionAddDto){
        return new ResponseEntity<>(inscriptionService.editInscriptionById(idInscription , inscriptionAddDto), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete inscription by ID, requires a valid JWT with role ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Delete inscription by ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InscriptionReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden, Access Denied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @DeleteMapping("{idInscription}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InscriptionReadDto> deleteInscriptionById(@PathVariable Integer idInscription) {
        return new ResponseEntity<InscriptionReadDto>(inscriptionService.deleteInscriptionById(idInscription), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Returns all inscriptions for one specific user, requires a valid JWT with role ADMIN or TEACHER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with all the inscriptions for one user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InscriptionReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden, Access Denied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping("user/{username}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ResponseEntity<List<CourseReadDto>> findAllInscriptions(@PathVariable String username) {
        return new ResponseEntity<>(inscriptionService.findAllCoursesInscriptedByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Returns all inscriptions for one specific course, requires a valid JWT with role ADMIN or TEACHER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with all the inscriptions for one course",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InscriptionReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden, Access Denied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping("course/{idCourse}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ResponseEntity<List<UserReadDto>> findAllInscriptedUsersByIdCourse(@PathVariable Integer idCourse) {
        return new ResponseEntity<>(inscriptionService.findAllInscriptedUsersByIdCourse(idCourse), HttpStatus.OK);
    }


}
