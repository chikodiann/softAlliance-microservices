package com.chikodiann.employee_mgmt.dto;

import com.chikodiann.employee_mgmt.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "SignUpDto",
        description = "Schema for signing up an employee"
)
public class SignUpDto {

    @Schema(description = "Employee's first name", example = "John")
    @NotEmpty(message = "First name is required")
    @Size(min = 3, message = "First name must be at least 3 characters")
    private String firstName;

    @Schema(description = "Employee's last name", example = "Doe")
    @NotEmpty(message = "Last name is required")
    @Size(min = 3, message = "Last name must be at least 3 characters")
    private String lastName;

    @Schema(description = "Valid email address", example = "john.doe@example.com")
    @Email(message = "Entry must be a valid email address")
    @NotEmpty(message = "Email address is required")
    private String email;

    @Schema(description = "Home address of the employee", example = "123 Main Street, Lagos")
    private String address;

    @Schema(description = "Role assigned to the employee", example = "MANAGER")
    private Role role;

    @Schema(description = "Phone number of the employee", example = "08012345678")
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    @Schema(description = "Password with minimum 8 characters, including uppercase, lowercase, digit, and special character", example = "Secure@123")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must contain at least one uppercase, lowercase, digit, special character, and be at least 8 characters long"
    )
    private String password;

    @Schema(description = "Password confirmation", example = "Secure@123")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$",
            message = "Confirm password must match password rules"
    )
    private String confirmPassword;

    @Schema(description = "ID of the department the employee belongs to", example = "3")
    private Long departmentId;
}
