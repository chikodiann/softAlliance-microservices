package com.chikodiann.authentication.dto;

import com.chikodiann.authentication.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "SignUpDto", description = "Payload to register a new employee")
public class SignUpDto {
    @Schema(description = "First name of the user", example = "Chikodi")
    @Size(min = 3, message = "First name must be at least 3 characters")
    @NotEmpty(message = "First name is required")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Anyanwu")
    @Size(min = 3, message = "Last name must be at least 3 characters")
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Schema(description = "User's email address", example = "chika@example.com")
    @Email(message = "Enter a valid email address")
    @NotEmpty(message = "Email address is required")
    private String email;

    @Schema(description = "User's home address", example = "23 Lekki Phase 1, Lagos")
    private String address;

    @Schema(description = "Role assigned to the user", example = "ADMIN")
    @NotNull(message = "Role is required")
    private Role role;

    @Schema(description = "User's phone number", example = "08012345678")
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    @Schema(description = "Password (must include uppercase, lowercase, digit, special character, min 8 chars)", example = "Pass@1234")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must have at least 8 characters, including uppercase, lowercase, digit, and special character"
    )
    private String password;

    @Schema(description = "Confirm password", example = "Pass@1234")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Confirm password must match the password rules"
    )
    private String confirmPassword;



}
