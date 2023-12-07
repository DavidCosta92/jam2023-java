package com.example.JAM23.demo.controllers;

import com.example.JAM23.demo.model.dtos.courses.CourseAddDto;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseReadDto>> showAllCourses() {
        return new ResponseEntity<>(courseService.showAllCourses(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseReadDto> showCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.showCourseById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CourseReadDto> createCourse( @RequestBody CourseAddDto courseAddDto ) {
        return new ResponseEntity<>(courseService.createCourse(courseAddDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseReadDto> editCourseById(@PathVariable Integer id, @RequestBody CourseAddDto courseAddDto ) {
        return new ResponseEntity<>(courseService.editCourseById(id,courseAddDto), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Integer id) {
        courseService.deleteCourseById(id);
        return new ResponseEntity<>("Curso eliminado!", HttpStatus.ACCEPTED);
    }

}
