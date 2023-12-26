package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.auth.User.User;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course/")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class CourseController {

    @Autowired
    private CourseService courseService;

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
    public ResponseEntity<List<CourseReadDto>> showAllCourses() {
        return new ResponseEntity<>(courseService.showAllCourses(), HttpStatus.OK);
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
    @GetMapping("{id}")
    public ResponseEntity<CourseReadDto> showCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.showCourseById(id), HttpStatus.OK);
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
