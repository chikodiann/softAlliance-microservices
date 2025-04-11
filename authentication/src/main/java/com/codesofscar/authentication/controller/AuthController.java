package com.codesofscar.authentication.controller;

import com.codesofscar.authentication.dto.ErrorResponseDTO;
import com.codesofscar.authentication.dto.LoginDto;
import com.codesofscar.authentication.dto.SignUpDto;
import com.codesofscar.authentication.dto.UserDto;
import com.codesofscar.authentication.repository.AuthRepository;
import com.codesofscar.authentication.service.impl.AuthServiceImpl;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "CRUD REST APIs for Authentication Management",
        description = "REST APIs to sign up or login users"
)
public class AuthController {
    private  AuthRepository authRepository;
    private AuthServiceImpl userService;

    @Autowired
    public AuthController(AuthServiceImpl userService, AuthRepository authRepository) {
        this.userService = userService;
        this.authRepository = authRepository;
    }

    @Operation(
            summary = "Sign Up Employee REST API",
            description = "REST API to sign up new employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpEmployee(@RequestBody SignUpDto signupDto, final HttpServletRequest request){
        userService.saveEmployee(signupDto);
        return new ResponseEntity<>("Signup successful", HttpStatus.OK);
    }

    @Operation(
            summary = "Sign Up Admin REST API",
            description = "REST API to sign up new admin"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )

    @PostMapping("/sign-up/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> signUpAdmin(@RequestBody SignUpDto adminDto, final HttpServletRequest request){
        userService.saveAdmin(adminDto);
        return new ResponseEntity<>("Admin signup successful", HttpStatus.OK);
    }

    @Operation(
            summary = "Login Employee REST API",
            description = "REST API to login employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String result = userService.logInEmployee(loginDto);
        return new ResponseEntity<>("Login successful!", HttpStatus.OK);
    }


    @Operation(
            summary = "Fetch Employee REST API",
            description = "REST API to fetch employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )


    @GetMapping("/generate-user")
    public ResponseEntity<UserDto> getUserForEmployee(Long userId){
        UserDto userDto  = userService.getUserForEmployee(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User For Employee REST API",
            description = "REST API to update employee user details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<String> updateUserForEmployee(@PathVariable Long userId, @RequestBody UserDto userDto){
        userService.updateUserForEmployee(userId, userDto);
        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User For Employee REST API",
            description = "REST API to delete user employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUserForEmployee(@PathVariable Long userId){
        userService.deleteUserForEmployee(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }


}
