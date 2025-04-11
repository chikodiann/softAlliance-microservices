package com.codesofscar.authentication.service.impl;

import com.codesofscar.authentication.dto.LoginDto;
import com.codesofscar.authentication.dto.SignUpDto;
import com.codesofscar.authentication.dto.UserDto;
import com.codesofscar.authentication.entity.Users;
import com.codesofscar.authentication.enums.Role;
import com.codesofscar.authentication.exception.*;
import com.codesofscar.authentication.mapper.UserMapper;
import com.codesofscar.authentication.repository.AuthRepository;
import com.codesofscar.authentication.service.AuthService;
import com.codesofscar.authentication.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private AuthRepository authRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;




    @Override
    public String logInEmployee(LoginDto userDto) {
        UserDetails user = loadUserByUsername(userDto.getEmail());
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new UserNotVerifiedException("Username and Password is Incorrect");
        }

        String jwtToken = jwtUtils.createJwt.apply(user);
        return jwtToken;
    }

    @Override
    public void saveEmployee(SignUpDto signupDto) {
        if (authRepository.existsByEmail(signupDto.getEmail())) {
            throw new EmailIsTakenException("Email is already taken, try Logging In or Signup with another email" );
        }
        Users user = new Users();

        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(signupDto.getConfirmPassword()));
        user.setFirstName(signupDto.getFirstName());
        user.setLastName(signupDto.getLastName());
        user.setPhoneNumber(signupDto.getPhoneNumber());
        user.setEmail(signupDto.getEmail());
        user.setAddress(signupDto.getAddress());
        user.setRole(signupDto.getRole());
        authRepository.save(user);
    }

    @Override
    public void saveAdmin(SignUpDto adminDto) {
        if (authRepository.existsByEmail(adminDto.getEmail())) {
            throw new EmailIsTakenException("Email is already taken, try Logging In or Signup with another email" );
        }
        Users user = new Users();

        user.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(adminDto.getConfirmPassword()));
        user.setFirstName(adminDto.getFirstName());
        user.setLastName(adminDto.getLastName());
        user.setPhoneNumber(adminDto.getPhoneNumber());
        user.setEmail(adminDto.getEmail());
        user.setAddress(adminDto.getAddress());
        user.setRole(Role.ADMIN);
        authRepository.save(user);
    }

    @Override
    public UserDto getUserForEmployee(Long userId) {
        Users user = authRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", userId.toString())
        );
        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not Found"));
    }

    @Override
    public void updateUserForEmployee(Long userId, UserDto employeeDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not Found"));


        if (user == null) {
            throw new UserNotFoundException("Admin must be Logged In to Continue");
        }
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new UserNotEligibleException("You are not allowed to Update Employees");
        }

        if (employeeDTO != null) {

            Users employee = authRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Employee not found"));
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(email);
            employee.setAddress(employeeDTO.getAddress());
            employee.setRole(employeeDTO.getRole());
            employee.setDepartmentId(employeeDTO.getDepartmentId());
            authRepository.save(employee);
        }

    }

    public void deleteUserForEmployee(Long userId) {
        Users employees = authRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Employee not found"));
        authRepository.delete(employees);
    }


}


