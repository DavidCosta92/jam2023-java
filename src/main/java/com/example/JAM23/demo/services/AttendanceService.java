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
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  InscriptionMapper inscriptionMapper;

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
        List <User> usersInscripted = userRepository.findAllInscriptedUsersIdByIdCourse(idCourse);

        if(usersInscripted.size() > 0){
            for (int i = 0; i< usersInscripted.size(); i++){
                List<AttendanceDto> attendanceList = getListAttendanceUserByIds(idCourse , usersInscripted.get(i).getId());
                UserAttendanceListDto userAttendanceListDto = new UserAttendanceListDto( userMapper.userEntityTOUserReadDto(usersInscripted.get(i)) , attendanceList);
                usersAttendanceList.add(userAttendanceListDto);
            }
        }
        return usersAttendanceList;
    }
    public AttendanceDto setAttendance (AttendanceDto attendanceDto){
        validator.existInscriptionById(attendanceDto.getId_inscription());
        validator.validateAttendanceDate(attendanceDto.getDate());

        // si viene sin ID, asumimos que es un nuevo registro, se pone presente true
        if(attendanceDto.getId_att() == null){
            attendanceDto.setPresent(true);
            AttendanceEntity attendanceEntity = attendanceMapper.attendanceDtoToAttendanceEntity(attendanceDto);
            attendanceRepository.save(attendanceEntity);
        } else{
            // si viene con ID, y en BD es false, lo cambiamos a true o viceversa
            Optional <AttendanceEntity> attendanceBD = attendanceRepository.findById(attendanceDto.getId_att());
            if (attendanceBD.isPresent()){
                boolean newStatus = attendanceBD.get().isPresent() == true? false : true;
                attendanceBD.get().setPresent(newStatus);
                attendanceRepository.save(attendanceBD.get());
            } else {
                // si viene con ID, pero no se encuetra error not found
                // TODO tirar error
                throw new NotFoundException("ID de asistencia no encontrado");
            }
        }
        AttendanceEntity attendance = attendanceRepository.findById(attendanceDto.getId_att()).get();
        return attendanceMapper.attendanceEntityToAttendanceDto(attendance);
    }
    public List<AttendanceDto> getListAttendanceUserByIds (Integer idCourse, Integer idUser){
        List<AttendanceDto> attendanceDtoList = new ArrayList<AttendanceDto>();
        // obtener el id de iscripcion en base al id de ususario
        Integer id_insc = inscriptionRepository.findByIdsCourseAndUser(idCourse , idUser).getId_insc();
        if (id_insc > 0){
            // si existe, obtener todos las asistencias basandas en el id de inscripcion
            List<AttendanceEntity> attendanceEntitiesList = attendanceRepository.findAttendanceListByIdInsc(id_insc);
            for (int i =0; i < attendanceEntitiesList.size() ; i++){
                attendanceDtoList.add(attendanceMapper.attendanceEntityToAttendanceDto(attendanceEntitiesList.get(i)));
            }
        }
        return attendanceDtoList;
    }

}
