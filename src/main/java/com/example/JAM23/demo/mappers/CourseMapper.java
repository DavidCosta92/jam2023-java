package com.example.JAM23.demo.mappers;

import com.example.JAM23.demo.model.dtos.courses.CourseAddDto;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseMapper {


    public CourseReadDto courseEntityTOCourseReadDto(CourseEntity courseEntity){
        return Optional
                .ofNullable(courseEntity)
                .map(entity-> new CourseReadDto(courseEntity.getId(), courseEntity.getIdTeacher(), courseEntity.getName(), courseEntity.getDuration() , courseEntity.getDescription()))
                .orElse(new CourseReadDto());
    }
    public CourseEntity courseAddDtoTOCourseEntity (CourseAddDto courseAddDto){
        return Optional
                .ofNullable(courseAddDto)
                .map(addDto-> {
                    CourseEntity entity = new CourseEntity();
                            entity.setIdTeacher(courseAddDto.getIdTeacher());
                            entity.setName(courseAddDto.getName());
                            entity.setDescription(courseAddDto.getDescription());
                            entity.setDuration(courseAddDto.getDuration());
                            return  entity;
                    })
                .orElse(new CourseEntity());
    }
    private Integer id;
    private Integer idTeacher;
    private String name;
    private Integer duration;
    private String description;
}