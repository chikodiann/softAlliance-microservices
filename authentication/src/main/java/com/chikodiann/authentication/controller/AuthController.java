package com.chikodiann.authentication.controller;

import com.chikodiann.authentication.dto.ErrorResponseDTO;
import com.chikodiann.authentication.dto.LoginDto;
import com.chikodiann.authentication.dto.SignUpDto;
import com.chikodiann.authentication.dto.UserDto;
import com.chikodiann.authentication.entity.Users;
import com.chikodiann.authentication.mapper.UserMapper;
import com.chikodiann.authentication.repository.AuthRepository;
import com.chikodiann.authentication.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "CRUD REST APIs for Authentication Management",
        description = "REST APIs to sign up or login users"
)
public class AuthController {
    private final AuthRepository authRepository;
    private final AuthServiceImpl userService;

    @Autowired
    public AuthController(AuthServiceImpl userService, AuthRepository authRepository) {
        this.userService = userService;
        this.authRepository = authRepository;
    }

    @Operation(summary = "Sign Up Employee REST API", description = "REST API to sign up new employee")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpEmployee(@RequestBody SignUpDto signupDto){
        userService.saveEmployee(signupDto);
        return new ResponseEntity<>("Signup successful", HttpStatus.OK);
    }

    @Operation(summary = "Sign Up Admin REST API", description = "REST API to sign up new admin")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    @PostMapping("/sign-up/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> signUpAdmin(@RequestBody SignUpDto adminDto, final HttpServletRequest request){
        userService.saveAdmin(adminDto);
        return new ResponseEntity<>("Admin signup successful", HttpStatus.OK);
    }

    @Operation(summary = "Signup Admin REST API", description = "REST API to login employee")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String token = userService.logInEmployee(loginDto);
        return ResponseEntity.ok("Bearer " + token);
    }

    @Operation(summary = "Fetch Employee REST API", description = "REST API to fetch employee")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    @GetMapping("/generate-user")
    public ResponseEntity<UserDto> getUserForEmployee(Long userId){
        UserDto userDto  = userService.getUserForEmployee(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Operation(summary = "Update User For Employee REST API", description = "REST API to update employee user details")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<String> updateUserForEmployee(@PathVariable Long userId, @RequestBody UserDto userDto){
        userService.updateUserForEmployee(userId, userDto);
        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }

    @Operation(summary = "Delete User For Employee REST API", description = "REST API to delete user employee")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUserForEmployee(@PathVariable Long userId){
        userService.deleteUserForEmployee(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/user-by-email")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'MANAGER')")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        Users user = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserDto dto = UserMapper.mapToUserDto(user, new UserDto());
        return ResponseEntity.ok(dto);
    }

}
