package com.example.JAM23.demo.model.dtos.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAddDto {
    private Integer idTeacher;
    private String name;
    private Integer duration;
    private String description;
}
