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

    @Operation(summary = "Shows courses, requires a valid JWT with READ_COURSES permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with all the courses",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseReadDto.class)) }) })
    @GetMapping
    @PreAuthorize("hasAuthority('READ_COURSES')") //@Secured({ "ADMIN", "TEACHER" })
    public ResponseEntity<List<CourseReadDto>> showAllCourses() {
        return new ResponseEntity<>(courseService.showAllCourses(), HttpStatus.OK);
    }

    @Operation(summary = "Shows course by ID, requires a valid JWT with READ_COURSES permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course by ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseReadDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, Error as result of sending invalid ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado por ID: id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('READ_COURSES')")
    public ResponseEntity<CourseReadDto> showCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.showCourseById(id), HttpStatus.OK);
    }

    @Operation(summary = "Creates course, requires a valid JWT with role ADMIN or TEACHER")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<CourseReadDto> createCourse( @RequestBody CourseAddDto courseAddDto ) {
        return new ResponseEntity<>(courseService.createCourse(courseAddDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Edit course by ID, requires a valid JWT with role ADMIN or TEACHER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseReadDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, Error as result of sending invalid ID",
                    content = @Content),
            @ApiResponse(responseCode = "406", description = "Not acceptable, Error as result of sending invalid data, Ex: 'ID curso incorrecto' ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionMessages.class)) }) })
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    // @PreAuthorize(hasRole("admin") orhasPrivil("read"))
    public ResponseEntity<CourseReadDto> editCourseById(@PathVariable Integer id, @RequestBody CourseAddDto courseAddDto ) {
        return new ResponseEntity<>(courseService.editCourseById(id,courseAddDto), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete course by ID, requires a valid JWT with role ADMIN or TEACHER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseReadDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, Error as result of sending invalid ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado por ID: id",
                    content = @Content) })
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<CourseReadDto> deleteCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.deleteCourseById(id), HttpStatus.ACCEPTED);
    }

}
