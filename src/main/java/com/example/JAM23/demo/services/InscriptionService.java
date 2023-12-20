package com.example.JAM23.demo.services;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.UserRepository;
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
import com.example.JAM23.demo.repositories.CourseRepository;
import com.example.JAM23.demo.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS
    // TODO REVISAR VALIDACIONES PREVIAS, POR EJEMPLO LAS CLAVES FORANEAS DEBEN RESPETAR QUE EXISTAN LOS CURSOS Y LOS USERS

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private InscriptionMapper inscriptionMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    public List<InscriptionReadDto> findAll (){
        List<InscriptionReadDto> listDto = new ArrayList<>();
        List<InscriptionEntity> listEntity =  inscriptionRepository.findAll();

        for (int i = 0; i < listEntity.size(); i++){
            listDto.add(inscriptionMapper.inscriptionEntityTOInscriptionReadDto(listEntity.get(i)));
        }
        return listDto;
    }

    public List<UserReadDto> findAllInscriptedUsersByIdCourse(Integer idCourse){
        List<User> inscriptedUsers = userRepository.findAllInscriptedUsersIdByIdCourse(idCourse);
        List<UserReadDto> inscriptedUsersDtos = new ArrayList<>();
        for (int i = 0; i<inscriptedUsers.size(); i++){
            inscriptedUsersDtos.add(userMapper.userEntityTOUserReadDto(inscriptedUsers.get(i)));
        }
        return inscriptedUsersDtos;
    }
    public InscriptionReadDto findInscriptionById(Integer idInscription){
        InscriptionEntity insc = inscriptionRepository.findById(idInscription).orElseThrow(()-> new NotFoundException("Inscripcion no encontrada"));
        UserReadDto user = userService.showUserById(insc.getUser().getId()); // getId_user_fk()
        CourseReadDto course = courseService.showCourseById(insc.getCourse().getId()); //getId_course_fk()

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
        List<CourseEntity> coursesEntities = courseRepository.findAllCoursesInscripted(username);
        List<CourseReadDto> courseReadDtos = new ArrayList<>();

        for(int i =0; i < coursesEntities.size() ; i++){
            courseReadDtos.add(courseMapper.courseEntityTOCourseReadDto(coursesEntities.get(i)));
        }
        return courseReadDtos;
    }

    public InscriptionReadDto editInscriptionById(Integer idInscription , InscriptionAddDto inscriptionAddDto){
        Integer id_user = inscriptionAddDto.getId_user();
        Integer id_course = inscriptionAddDto.getId_course();

        Optional<InscriptionEntity> inscriptionEntity = inscriptionRepository.findById(idInscription);

        if(inscriptionEntity.isPresent()){
            /*
            Integer id_user_fk = inscriptionEntity.get().getId_user_fk();
            Integer id_course_fk = inscriptionEntity.get().getId_course_fk();


            if(id_user !=null && id_course !=null){
                inscriptionEntity.get().setId(new InscriptionKey( id_user_fk , id_course_fk));
            } else {
                if(id_user !=null) inscriptionEntity.get().setId(new InscriptionKey( id_user , id_course_fk)); ; // .setId_user_fk(id_user);
                if(id_course !=null) inscriptionEntity.get().setId(new InscriptionKey( id_user_fk, id_course)); ; // .setId_course_fk(id_course);
            }
             */
/*

ESTO ESTA COMENTADO PORQUE NO PUEDO MODIFICAR LOS ID SI TENGO UNA ENTIDAD USUARIO, DEBERIA CAMBIAR EL USUARIO COMO ENTIDAD Y NO COMO UN NUMERO DE USUER


            if(id_user !=null) inscriptionEntity.get().setId_user_fk(id_user); //.setId_user_fk(id_user);
            if(id_course !=null) inscriptionEntity.get().setId_course_fk(id_course); //.setId_course_fk(id_course);
            inscriptionRepository.save(inscriptionEntity.get());

 */
        }
        return inscriptionMapper.inscriptionEntityTOInscriptionReadDto(inscriptionEntity.get());

    }
    public void deleteInscriptionById(Integer idInscription){
        inscriptionRepository.deleteById(idInscription);
    }
}
