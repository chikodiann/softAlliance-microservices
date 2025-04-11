package com.chikodiann.authentication.service.impl;

import com.chikodiann.authentication.dto.LoginDto;
import com.chikodiann.authentication.dto.SignUpDto;
import com.chikodiann.authentication.dto.UserDto;
import com.chikodiann.authentication.enums.Role;
import com.chikodiann.authentication.exception.*;
import com.chikodiann.authentication.utils.JwtUtils;
import com.chikodiann.authentication.entity.Users;
import com.chikodiann.authentication.mapper.UserMapper;
import com.chikodiann.authentication.repository.AuthRepository;
import com.chikodiann.authentication.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

        return jwtUtils.createJwt.apply(user);
    }

    private Users buildUserFromDto(SignUpDto dto, Role role) {
        Users user = new Users();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        user.setRole(role);
        return user;
    }


    @Override
    public void saveEmployee(SignUpDto signupDto) {
        if (authRepository.existsByEmail(signupDto.getEmail())) {
            throw new EmailIsTakenException("Email is already taken, try Logging In or Signup with another email" );
        }
        if (!signupDto.getPassword().equals(signupDto.getConfirmPassword())) {
            throw new PasswordsDontMatchException("Passwords do not match");
        }
        authRepository.save(buildUserFromDto(signupDto, signupDto.getRole()));
    }

    @Override
    public void saveAdmin(SignUpDto adminDto) {
        if (authRepository.existsByEmail(adminDto.getEmail())) {
            throw new EmailIsTakenException("Email is already taken, try Logging In or Signup with another email" );
        }
        if (!adminDto.getPassword().equals(adminDto.getConfirmPassword())) {
            throw new PasswordsDontMatchException("Passwords do not match");
        }
        authRepository.save(buildUserFromDto(adminDto, Role.ADMIN));
    }

    @Override
    public UserDto getUserForEmployee(Long userId) {
        Users user = authRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", userId.toString())
        );
        return UserMapper.mapToUserDto(user, new UserDto());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not Found"));
    }

    @Override
    @Transactional
    public void updateUserForEmployee(Long userId, UserDto employeeDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not Found"));

        // Check if the authenticated user is an admin
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new UserNotEligibleException("You are not allowed to Update Employees");
        }

        if (employeeDTO != null) {

            Users employee = authRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Employee not found"));
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setAddress(employeeDTO.getAddress());
            employee.setRole(employeeDTO.getRole());
            employee.setDepartmentId(employeeDTO.getDepartmentId());
            authRepository.save(employee);
        }
        log.info("Admin {} updated employee {}", email, userId);
    }

    @Transactional
    public void deleteUserForEmployee(Long userId) {
        Users employee = authRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Employee not found"));
        authRepository.delete(employee);
        log.info("Admin {} deleted employee {}", employee, userId);
    }


}


