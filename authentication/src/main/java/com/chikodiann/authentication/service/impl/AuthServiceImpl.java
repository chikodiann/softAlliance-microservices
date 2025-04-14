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
        log.info("Attempting login for user: {}", userDto.getEmail());
        UserDetails user = loadUserByUsername(userDto.getEmail());

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            log.error("Login failed: Incorrect username or password for user: {}", userDto.getEmail());
            throw new UserNotVerifiedException("Username and Password is Incorrect");
        }

        log.info("Login successful for user: {}", userDto.getEmail());
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
        log.info("Building user from DTO: {}", user);
        return user;
    }

    @Override
    public void saveEmployee(SignUpDto signupDto) {
        log.info("Saving employee with email: {}", signupDto.getEmail());

        if (authRepository.existsByEmail(signupDto.getEmail())) {
            log.error("Email is already taken: {}", signupDto.getEmail());
            throw new EmailIsTakenException("Email is already taken, try Logging In or Signup with another email");
        }

        if (!signupDto.getPassword().equals(signupDto.getConfirmPassword())) {
            log.error("Passwords do not match for email: {}", signupDto.getEmail());
            throw new PasswordsDontMatchException("Passwords do not match");
        }

        authRepository.save(buildUserFromDto(signupDto, signupDto.getRole()));
        log.info("Employee signed up successfully: {}", signupDto.getEmail());
    }

    @Override
    public void saveAdmin(SignUpDto adminDto) {
        log.info("Saving admin with email: {}", adminDto.getEmail());

        if (authRepository.existsByEmail(adminDto.getEmail())) {
            log.error("Email is already taken for admin: {}", adminDto.getEmail());
            throw new EmailIsTakenException("Email is already taken, try Logging In or Signup with another email");
        }

        if (!adminDto.getPassword().equals(adminDto.getConfirmPassword())) {
            log.error("Passwords do not match for admin: {}", adminDto.getEmail());
            throw new PasswordsDontMatchException("Passwords do not match");
        }

        authRepository.save(buildUserFromDto(adminDto, Role.ADMIN));
        log.info("Admin signed up successfully: {}", adminDto.getEmail());
    }

    @Override
    public UserDto getUserForEmployee(Long userId) {
        log.info("Fetching user details for employee with ID: {}", userId);
        Users user = authRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", userId.toString())
        );
        UserDto userDto = UserMapper.mapToUserDto(user, new UserDto());
        log.info("Fetched user details for employee: {}", userDto);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by email: {}", email);
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

        log.info("Admin {} is attempting to update employee details for employee ID: {}", email, userId);

        // Check if the authenticated user is an admin
        if (!user.getRole().equals(Role.ADMIN)) {
            log.error("Unauthorized attempt by user {} to update employee details for employee ID: {}", email, userId);
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
            log.info("Employee details updated successfully for employee ID: {}", userId);
        }
    }

    @Transactional
    public void deleteUserForEmployee(Long userId) {
        log.info("Attempting to delete employee with ID: {}", userId);
        Users employee = authRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Employee not found"));
        authRepository.delete(employee);
        log.info("Employee deleted successfully: {}", employee);
    }
}
