package com.codesofscar.authentication.service;

import com.codesofscar.authentication.dto.LoginDto;
import com.codesofscar.authentication.dto.SignUpDto;
import com.codesofscar.authentication.dto.UserDto;

public interface AuthService {

    String logInEmployee(LoginDto userDto);
    void saveEmployee(SignUpDto signupDto);
    void saveAdmin(SignUpDto signupDto);

    UserDto getUserForEmployee(Long userId);

    void updateUserForEmployee(Long userId, UserDto employeeDTO);
}
