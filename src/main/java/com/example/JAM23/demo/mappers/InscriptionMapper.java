package com.example.JAM23.demo.mappers;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.UserRepository;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionAddDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import com.example.JAM23.demo.repositories.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class InscriptionMapper {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public InscriptionMapper(UserRepository userRepository,
                             CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public InscriptionReadDto inscriptionEntityTOInscriptionReadDto(InscriptionEntity inscriptionEntity){
        CourseEntity courseEntity = inscriptionEntity.getCourse();
        User userEntity = inscriptionEntity.getUser();

        return Optional
                .ofNullable(inscriptionEntity)
                .map(entity->{
                    return InscriptionReadDto.builder()
                            .id(inscriptionEntity.getId_insc())
                            .id_course(courseEntity.getId())
                            .id_teacher(courseEntity.getIdTeacher())
                            .courseName(courseEntity.getName())
                            .id_user(userEntity.getId())
                            .username(userEntity.getUsername())
                            .email(userEntity.getEmail())
                            .lastName(userEntity.getLastName())
                            .firstName(userEntity.getFirstName())
                            .build();
                })
                .orElse(new InscriptionReadDto());
    }

    public InscriptionEntity inscriptionAddDtoToEntity (InscriptionAddDto  addDto){
        User user = userRepository.findById(addDto.getId_user()).get();
        CourseEntity course = courseRepository.findById(addDto.getId_course()).get();

        return Optional
                .ofNullable(addDto)
                .map(entity ->{
                    return InscriptionEntity.builder()
                            .user(user)
                            .course(course)
                            .build();
                })
                .orElse(new InscriptionEntity());
    }
}
