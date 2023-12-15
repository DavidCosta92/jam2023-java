package com.example.JAM23.demo.services;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.UserRepository;
import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.CourseMapper;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import com.example.JAM23.demo.repositories.InscriptionRepository;
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

    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    public List<InscriptionReadDto> findAll (){
        return new ArrayList<>();//inscriptionRepository.findAll();
    }
    public InscriptionReadDto findInscriptionById(Integer idInscription){
        InscriptionEntity insc = inscriptionRepository.findById(idInscription).orElseThrow(()-> new NotFoundException("Inscripcion no encontrada"));
        UserReadDto user = userService.showUserById(insc.getId_user_fk());
        CourseReadDto course = courseService.showCourseById(insc.getId_course_fk());

        InscriptionReadDto inscription = new InscriptionReadDto();
        inscription.setId(insc.getId());
        //datos user
        inscription.setId_user(insc.getId_user_fk());
        inscription.setUsername(user.getUsername());
        inscription.setEmail(user.getEmail());
        inscription.setFirstName(user.getFirstName());
        inscription.setLastName(user.getLastName());

        //datos course
        inscription.setId_course(insc.getId_course_fk());
        inscription.setId_teacher(course.getIdTeacher());
        inscription.setCourseName(course.getName());

        return  inscription;
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
