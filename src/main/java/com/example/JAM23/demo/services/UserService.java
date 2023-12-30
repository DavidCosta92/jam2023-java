package com.example.JAM23.demo.services;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.auth.UserRepository;
import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.UserMapper;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseService courseService;

    public User getUserById (Integer id){
        try {
            return userRepository.findById(id).get();
        }catch (Exception e){
            throw new NotFoundException("Usuario no econtrado con ID: "+id);
        }
    }

    public List<UserReadDto> findAllInscriptedUsersIdByIdCourse (Integer idCourse){
        courseService.getCourseById(idCourse); // Si no existe curso, lanza exepcion not found

        List<User> inscriptedUsers = userRepository.findAllInscriptedUsersIdByIdCourse(idCourse);

        List<UserReadDto> inscriptedUsersDtos = new ArrayList<>();
        for (int i = 0; i<inscriptedUsers.size(); i++){
            inscriptedUsersDtos.add(userMapper.userEntityTOUserReadDto(inscriptedUsers.get(i)));
        }
        return inscriptedUsersDtos;
    }
}
