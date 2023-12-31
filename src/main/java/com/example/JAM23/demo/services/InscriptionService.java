package com.example.JAM23.demo.services;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.CourseMapper;
import com.example.JAM23.demo.mappers.InscriptionMapper;
import com.example.JAM23.demo.mappers.UserMapper;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionAddDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import com.example.JAM23.demo.repositories.InscriptionRepository;
import com.example.JAM23.demo.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private InscriptionMapper inscriptionMapper;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Validator validator;

    public List<InscriptionReadDto> findAll (){
        List<InscriptionReadDto> listDto = new ArrayList<>();
        List<InscriptionEntity> listEntity =  inscriptionRepository.findAll();
        for (int i = 0; i < listEntity.size(); i++){
            listDto.add(inscriptionMapper.inscriptionEntityTOInscriptionReadDto(listEntity.get(i)));
        }
        return listDto;
    }
    public InscriptionReadDto createInscription(InscriptionAddDto addDto){
        validator.existUserById(addDto.getId_user());
        validator.existCourseById(addDto.getId_course());
        validator.alreadyExistInscription(addDto);
        return Optional
                .ofNullable(addDto)
                .map(add -> inscriptionMapper.inscriptionAddDtoToEntity(addDto))
                .map(entity ->inscriptionRepository.save(entity))
                .map(entity -> inscriptionMapper.inscriptionEntityTOInscriptionReadDto(entity))
                .orElse(new InscriptionReadDto());
    }
    public List<UserReadDto> findAllInscriptedUsersByIdCourse(Integer idCourse){
        return userService.findAllInscriptedUsersIdByIdCourse(idCourse);
    }
    public InscriptionReadDto findInscriptionById(Integer idInscription){
        InscriptionEntity insc = inscriptionRepository.findById(idInscription).orElseThrow(()-> new NotFoundException("Inscripcion no encontrada"));
        User user = userService.getUserById(insc.getUser().getId()); // getId_user_fk()
        CourseEntity course = courseService.getCourseById(insc.getCourse().getId()); //getId_course_fk()

        InscriptionReadDto inscription = new InscriptionReadDto();
        inscription.setId(insc.getId_insc());
        //datos user
        inscription.setId_user(insc.getUser().getId()); //.getId_user_fk()
        inscription.setUsername(user.getUsername());
        inscription.setEmail(user.getEmail());
        inscription.setFirstName(user.getFirstName());
        inscription.setLastName(user.getLastName());

        //datos course
        inscription.setId_course(insc.getCourse().getId()); //getId_course_fk()
        inscription.setId_teacher(course.getIdTeacher());
        inscription.setCourseName(course.getName());

        return  inscription;
    }
    public List<CourseReadDto> findAllCoursesInscriptedByUsername(String username){
        return courseService.findAllCoursesInscriptedByUsername(username);
    }
    public InscriptionReadDto editInscriptionById(Integer idInscription , InscriptionAddDto inscriptionAddDto){
        Integer id_user = inscriptionAddDto.getId_user();
        Integer id_course = inscriptionAddDto.getId_course();

        Optional<InscriptionEntity> inscriptionEntity = inscriptionRepository.findById(idInscription);

        if(inscriptionEntity.isPresent()){
            if(id_user !=null) {
                validator.existUserById(id_user);
                User user = userService.getUserById(id_user);
                inscriptionEntity.get().setUser(user);
            }
            if(id_course !=null) {
                validator.existCourseById(id_course);
                CourseEntity course = courseService.getCourseById(id_course);
                inscriptionEntity.get().setCourse(course);
            }
            inscriptionRepository.save(inscriptionEntity.get());
        } else {
            throw new NotFoundException("Inscripcion no encontrada con ID: " + idInscription);
        }
        return inscriptionMapper.inscriptionEntityTOInscriptionReadDto(inscriptionEntity.get());
    }
    public InscriptionReadDto deleteInscriptionById(Integer idInscription){
        Optional<InscriptionEntity> insc = inscriptionRepository.findById(idInscription);
        if (insc.isPresent()){
            inscriptionRepository.deleteById(idInscription);
            return inscriptionMapper.inscriptionEntityTOInscriptionReadDto(insc.get());
        } else {
            throw new NotFoundException("Inscripcion no encontrada con ID: "+idInscription);
        }
    }

    public Integer getIdInscriptionByIdsCourseAndUser (Integer idCourse, Integer idUser){
        try{
            return inscriptionRepository.findByIdsCourseAndUser(idCourse , idUser).getId_insc();
        } catch (Exception e){
            throw new NotFoundException("Inscripcion al curso: "+idCourse+" del usuario: "+idUser+", no existe!");
        }
    }
}
