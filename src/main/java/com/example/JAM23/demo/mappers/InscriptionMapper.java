package com.example.JAM23.demo.mappers;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class InscriptionMapper {
    public InscriptionReadDto inscriptionEntityTOInscriptionReadDto(InscriptionEntity inscriptionEntity){
        CourseEntity courseEntity = inscriptionEntity.getCourse();
        User userEntity = inscriptionEntity.getUser();

        return Optional
                .ofNullable(inscriptionEntity)
                .map(entity -> {
                    InscriptionReadDto insc = new InscriptionReadDto();
                    insc.setId(inscriptionEntity.getId_insc());
                    insc.setId_course(courseEntity.getId());
                    insc.setId_teacher(courseEntity.getIdTeacher());
                    insc.setCourseName(courseEntity.getName());
                    insc.setId_user(userEntity.getId());
                    insc.setUsername(userEntity.getUsername());
                    insc.setEmail(userEntity.getEmail());
                    insc.setLastName(userEntity.getLastName());
                    insc.setFirstName(userEntity.getFirstName());
                    return insc;
                })
                .orElse(new InscriptionReadDto());
    }

}
