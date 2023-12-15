package com.example.JAM23.demo.services;

import com.example.JAM23.demo.auth.UserRepository;
import com.example.JAM23.demo.exception.customsExceptions.NotFoundException;
import com.example.JAM23.demo.mappers.UserMapper;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;


    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserReadDto showUserById (Integer id){
        return Optional
                .ofNullable(id)
                .map(user -> userRepository.findById(id).get())
                .map(userEntity -> userMapper.userEntityTOUserReadDto(userEntity))
                // .orElse(new CourseReadDto());
                .orElseThrow(()->new NotFoundException("Usuario no encontrado por ID: "+id));
    }
}
