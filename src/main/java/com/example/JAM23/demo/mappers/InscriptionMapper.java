package com.example.JAM23.demo.mappers;

import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class InscriptionMapper {
    public InscriptionReadDto inscriptionEntityTOInscriptionReadDto(InscriptionEntity inscriptionEntity){
        return Optional
                .ofNullable(inscriptionEntity)
                .map(entity -> {
                    InscriptionReadDto insc = new InscriptionReadDto();
                    insc.setId_course(inscriptionEntity.getCourse().getId()); //.getId_course_fk());
                    insc.setId_user(inscriptionEntity.getUser().getId()); //.getId_user_fk());
                    return insc;
                })
                .orElse(new InscriptionReadDto());
    }
}
