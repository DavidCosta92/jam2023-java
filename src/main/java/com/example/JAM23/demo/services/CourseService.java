package com.example.JAM23.demo.services;

import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.CourseMapper;
import com.example.JAM23.demo.model.dtos.courses.CourseAddDto;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

    public CourseReadDto createCourse (CourseAddDto courseAddDto){
        // validarCourseAddDto()
        return Optional
                .ofNullable(courseAddDto)
                .map(addDto -> courseMapper.courseAddDtoTOCourseEntity(addDto))
                .map(courseEntity -> courseRepository.save(courseEntity))
                .map(courseEntity -> courseMapper.courseEntityTOCourseReadDto(courseEntity))
                .orElse(new CourseReadDto()); //.orElseThrow(()->new RuntimeException("Error creando cruso?"));
    }

    public List<CourseReadDto> showAllCourses (){
        List<CourseReadDto> allCourses = new ArrayList<>();
        List<CourseEntity> allCoursesEntity = courseRepository.findAll();
        if (allCoursesEntity.size()>0){
            // allCoursesEntity.stream().map(entity -> courseMapper.CourseEntityTOCourseReadDto(entity));
            for (int i = 0; i <allCoursesEntity.size() ; i++){
                CourseReadDto courseDto = courseMapper.courseEntityTOCourseReadDto(allCoursesEntity.get(i));
                allCourses.add(courseDto);
            }
        }
        return allCourses;
    }
    public CourseReadDto showCourseById (Integer idCourse){
        return Optional
                .ofNullable(idCourse)
                .map(course -> courseRepository.findById(idCourse).get())
                .map(courseEntity -> courseMapper.courseEntityTOCourseReadDto(courseEntity))
                // .orElse(new CourseReadDto());
                .orElseThrow(()->new NotFoundException("Curso no encontrado por ID: "+idCourse));
    }

    public CourseReadDto editCourseById(Integer idCourse, CourseAddDto course){
        Integer idTeacher = course.getIdTeacher();
        String name = course.getName();
        Integer duration = course.getDuration();
        String description = course.getDescription();

        Optional<CourseEntity> bdEntity = courseRepository.findById(idCourse);
        if (bdEntity.isPresent()){
            if (idTeacher != null) bdEntity.get().setIdTeacher(idTeacher);
            if (name != null) bdEntity.get().setName(name);
            if (duration != null) bdEntity.get().setDuration(duration);
            if (description != null) bdEntity.get().setDescription(description);
            courseRepository.save(bdEntity.get());
        }
        return courseMapper.courseEntityTOCourseReadDto(bdEntity.get());
    }

    public void deleteCourseById (Integer id){
        courseRepository.deleteById(id);
    }
}
