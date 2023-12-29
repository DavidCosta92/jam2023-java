package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.exception.ExceptionMessages;
import com.example.JAM23.demo.model.dtos.courses.CourseAddDto;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.entities.AttendanceEntity;
import com.example.JAM23.demo.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
// @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')") //@Secured({ "ADMIN", "TEACHER" })
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Operation(summary = "Shows courses, requires a valid JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with all the courses",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseReadDto.class)) }) })
    @GetMapping
    @PreAuthorize("hasAuthority('READ_COURSES')") //@Secured({ "ADMIN", "TEACHER" })
    public ResponseEntity<List<CourseReadDto>> showAllCourses() {
        return new ResponseEntity<>(courseService.showAllCourses(), HttpStatus.OK);
    }

    @Operation(summary = "Shows course by ID, requires a valid JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course by ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseReadDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado por ID: id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping("{id}")
    public ResponseEntity<CourseReadDto> showCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.showCourseById(id), HttpStatus.OK);
    }

    @Operation(summary = "Creates course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseReadDto.class)) }),
            @ApiResponse(responseCode = "406", description = "Not acceptable, Error as result of sending invalid data, Ex: 'ID profesor incorrecto' ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict, Error as result of sending data already reported, Ex: 'Ya existe curso con el nombre matematica III' ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @PostMapping
    public ResponseEntity<CourseReadDto> createCourse( @RequestBody CourseAddDto courseAddDto ) {
        return new ResponseEntity<>(courseService.createCourse(courseAddDto), HttpStatus.CREATED);
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
    @PutMapping("{id}")
    // @PreAuthorize(hasRole("admin") orhasPrivil("read"))
    public ResponseEntity<CourseReadDto> editCourseById(@PathVariable Integer id, @RequestBody CourseAddDto courseAddDto ) {
        return new ResponseEntity<>(courseService.editCourseById(id,courseAddDto), HttpStatus.ACCEPTED);
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
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Integer id) {
        courseService.deleteCourseById(id);
        return new ResponseEntity<>("Curso eliminado!", HttpStatus.ACCEPTED);
    }

}
