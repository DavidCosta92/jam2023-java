package com.example.JAM23.demo.mappers;

import com.example.JAM23.demo.auth.User.User;
import com.example.JAM23.demo.model.dtos.courses.CourseReadDto;
import com.example.JAM23.demo.model.dtos.users.UserReadDto;
import com.example.JAM23.demo.model.entities.CourseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public UserReadDto userEntityTOUserReadDto(User user){
        return Optional
                .ofNullable(user)
                .map(entity-> {
                    UserReadDto userDto = new UserReadDto();
                    userDto.setId(user.getId());
                    userDto.setUsername(user.getUsername());
                    userDto.setPassword(user.getPassword());
                    userDto.setFirstName(user.getFirstName());
                    userDto.setLastName(user.getLastName());
                    userDto.setDni(user.getDni());
                    userDto.setEmail(user.getEmail());
                    userDto.setPhone(user.getPhone());
                    userDto.setGender(user.getGender());
                    return userDto;
                })
                .orElse(new UserReadDto());
    }
}