package com.chikodiann.authentication.service;

import com.chikodiann.authentication.dto.LoginDto;
import com.chikodiann.authentication.dto.SignUpDto;
import com.chikodiann.authentication.dto.UserDto;

public interface AuthService {

    String logInEmployee(LoginDto userDto);
    void saveEmployee(SignUpDto signupDto);
    void saveAdmin(SignUpDto signupDto);

    UserDto getUserForEmployee(Long userId);

    void updateUserForEmployee(Long userId, UserDto employeeDTO);
}
