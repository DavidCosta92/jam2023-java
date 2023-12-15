package com.example.JAM23.demo.services;

import com.example.JAM23.demo.mappers.CourseMapper;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InscriptionService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

    public CourseReadDto findInscriptionById(Integer idCourse){
        return courseMapper.courseEntityTOCourseReadDto(courseRepository.findById(idCourse).get());
    }

    public List<CourseReadDto> findAllCoursesInscriptedByUsername(String username){
        List<CourseEntity> coursesEntities = courseRepository.findAllCoursesInscripted(username);
        List<CourseReadDto> courseReadDtos = new ArrayList<>();

        for(int i =0; i < coursesEntities.size() ; i++){
            courseReadDtos.add(courseMapper.courseEntityTOCourseReadDto(coursesEntities.get(i)));
        }
        return courseReadDtos;
    }
}
