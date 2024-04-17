package com.example.JAM23.demo.services;

import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.CourseMapper;
import com.example.JAM23.demo.model.dtos.courses.CourseAddDto;
import com.example.JAM23.demo.model.dtos.courses.CourseListReadDto;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import com.example.JAM23.demo.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private Validator validator;

    // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;
    // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;
    // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;
    // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;
    // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;
    // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;


    public CourseReadDto createCourse (CourseAddDto courseAddDto){

        // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;

        validator.emptyString("Name", courseAddDto.getName());
        validator.emptyString("Description", courseAddDto.getDescription());

        validator.existTeacherById(courseAddDto.getIdTeacher());
        validator.alreadyExistCourseByName(courseAddDto.getName());

        return Optional
                .ofNullable(courseAddDto)
                .map(addDto -> courseMapper.courseAddDtoTOCourseEntity(addDto))
                .map(courseEntity -> courseRepository.save(courseEntity))
                .map(courseEntity -> courseMapper.courseEntityTOCourseReadDto(courseEntity))
                .orElse(new CourseReadDto()); //.orElseThrow(()->new RuntimeException("Error creando cruso?"));
    }
    public CourseListReadDto showAllCourses (Integer page, Integer size, String sortBy){
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CourseEntity> results = courseRepository.findAll(pageable);

        return CourseListReadDto.builder()
                .courses(results.getContent())
                .total_results(results.getTotalElements())
                .results_per_page(size)
                .sort_by(sortBy)
                .pages(results.getTotalPages())
                .current_page(page)
                .build();
    }
    public CourseReadDto showCourseById (Integer idCourse){
        return courseMapper.courseEntityTOCourseReadDto(getCourseById(idCourse));
    }
    public CourseEntity getCourseById(Integer idCourse){
        try {
           return courseRepository.findById(idCourse).get();
        }catch (Exception e){
            throw new NotFoundException("Curso no encontrado por ID: "+idCourse);
        }
    }
    public CourseReadDto editCourseById(Integer idCourse, CourseAddDto course){

        validator.existCourseById(idCourse);

        Integer idTeacher = course.getIdTeacher();
        String name = course.getName();
        Integer duration = course.getDuration();
        String description = course.getDescription();

        Optional<CourseEntity> bdEntity = courseRepository.findById(idCourse);
        if (bdEntity.isPresent()){
            if (idTeacher != null) {
                validator.existTeacherById(idTeacher);
                bdEntity.get().setIdTeacher(idTeacher);
            }
            if (name != null) {
                validator.emptyString("Name", name);
                bdEntity.get().setName(name);
            }
            if (duration != null) {
                // todo validar que sea un valor de tiempo?? pasar dato a fecha?? como manejarlo? Integer duration;
                bdEntity.get().setDuration(duration);
            }
            if (description != null){
                validator.emptyString("Description", description);
                bdEntity.get().setDescription(description);
            }
            courseRepository.save(bdEntity.get());
        }
        return courseMapper.courseEntityTOCourseReadDto(bdEntity.get());
    }

    public CourseReadDto deleteCourseById (Integer idCourse){
        Optional<CourseEntity> courseToDelete = courseRepository.findById(idCourse);
        if (courseToDelete.isPresent()){
            courseRepository.deleteById(idCourse);
            return courseMapper.courseEntityTOCourseReadDto(courseToDelete.get());
        }else{
            throw new NotFoundException("Curso no encontrado por ID: "+idCourse);
        }
    }

    public List<CourseReadDto> findAllCoursesInscriptedByUsername( String username){
        if(validator.existUserByUsername(username)){
            List<CourseEntity> coursesEntities = courseRepository.findAllCoursesInscripted(username);
            List<CourseReadDto> courseReadDtos = new ArrayList<>();

            for(int i =0; i < coursesEntities.size() ; i++){
                courseReadDtos.add(courseMapper.courseEntityTOCourseReadDto(coursesEntities.get(i)));
            }
            return courseReadDtos;
        } else {
            throw new NotFoundException("Usuario no ecnontrado con username: "+username);
        }
    }
}
