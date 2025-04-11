package com.chikodiann.authentication.mapper;

import com.chikodiann.authentication.dto.UserDto;
import com.chikodiann.authentication.entity.Users;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserMapper {

    public static UserDto mapToUserDto(Users user, UserDto userDto) {
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setDepartmentId(user.getDepartmentId());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static Users mapToUser(UserDto userDto, Users user) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setDepartmentId(userDto.getDepartmentId());
        user.setRole(userDto.getRole());
        return user;
    }
}