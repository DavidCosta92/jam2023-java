package com.example.JAM23.demo.services;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.UserRepository;
import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.AttendanceMapper;
import com.example.JAM23.demo.mappers.InscriptionMapper;
import com.example.JAM23.demo.mappers.UserMapper;
import com.example.JAM23.demo.model.dtos.attendances.AttendanceDto;
import com.example.JAM23.demo.model.dtos.attendances.UserAttendanceListDto;
import com.example.JAM23.demo.model.dtos.inscriptions.InscriptionReadDto;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import com.example.JAM23.demo.model.entities.AttendanceEntity;
import com.example.JAM23.demo.model.entities.InscriptionEntity;
import com.example.JAM23.demo.repositories.AttendanceRepository;
import com.example.JAM23.demo.repositories.InscriptionRepository;
import com.example.JAM23.demo.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private  InscriptionMapper inscriptionMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Validator validator;

    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO => Cual seria el caso practico? equivocarnos de user o de curso?
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO => Cual seria el caso practico? equivocarnos de user o de curso?
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO => Cual seria el caso practico? equivocarnos de user o de curso?
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO => Cual seria el caso practico? equivocarnos de user o de curso?
    // TODO MODIFICAR ASISTENCIA DE TAL USER A TAL CURSO => Cual seria el caso practico? equivocarnos de user o de curso?

    public List<UserAttendanceListDto> getUserAttendanceListByIdCourse (Integer idCourse){
        List <UserAttendanceListDto> usersAttendanceList = new ArrayList<>();
        List <UserReadDto> usersInscripted = userService.findAllInscriptedUsersIdByIdCourse(idCourse);

        if(usersInscripted.size() > 0){
            for (int i = 0; i< usersInscripted.size(); i++){
                List<AttendanceDto> attendanceList = getListAttendanceUserByIds(idCourse , usersInscripted.get(i).getId());
                UserAttendanceListDto userAttendanceListDto = new UserAttendanceListDto( usersInscripted.get(i) , attendanceList);
                usersAttendanceList.add(userAttendanceListDto);
            }
        }
        return usersAttendanceList;
    }
    public AttendanceDto setAttendance (AttendanceDto attendanceDto){
        if(attendanceDto.getId_att() == null){
            // si viene sin ID, asumimos que es un nuevo registro, se pone presente true, valida datos, los guarda y devuelve
            validator.alreadyExistAttendance(attendanceDto);
            validator.existInscriptionById(attendanceDto.getId_inscription());
            validator.validateAttendanceDate(attendanceDto.getDate());
            attendanceDto.setPresent(true);
            AttendanceEntity attendanceEntity = attendanceMapper.attendanceDtoToAttendanceEntity(attendanceDto);
            attendanceRepository.save(attendanceEntity);
            return attendanceMapper.attendanceEntityToAttendanceDto(attendanceEntity);
        } else{
            // si viene con ID, Solo se cambia el estado del presente, pero el resto de datos no! y en BD es false, lo cambiamos a true o viceversa
            Optional <AttendanceEntity> attendanceBD = attendanceRepository.findById(attendanceDto.getId_att());
            if (attendanceBD.isPresent()){
                boolean newStatus = attendanceBD.get().isPresent() == true? false : true;
                attendanceBD.get().setPresent(newStatus);
                attendanceRepository.save(attendanceBD.get());
                AttendanceEntity attendance = attendanceRepository.findById(attendanceDto.getId_att()).get();
                return attendanceMapper.attendanceEntityToAttendanceDto(attendance);
            } else {
                throw new NotFoundException("ID de asistencia no encontrado");
            }
        }
    }
    public List<AttendanceDto> getListAttendanceUserByIds (Integer idCourse, Integer idUser){
        List<AttendanceDto> attendanceDtoList = new ArrayList<AttendanceDto>();
        // obtener el id de iscripcion en base al id de ususario y curso
        Integer id_insc = inscriptionService.getIdInscriptionByIdsCourseAndUser(idCourse , idUser);
        List<AttendanceEntity> attendanceEntitiesList = attendanceRepository.findAttendanceListByIdInsc(id_insc);
        for (int i =0; i < attendanceEntitiesList.size() ; i++){
            attendanceDtoList.add(attendanceMapper.attendanceEntityToAttendanceDto(attendanceEntitiesList.get(i)));
        }
        return attendanceDtoList;
    }

}
