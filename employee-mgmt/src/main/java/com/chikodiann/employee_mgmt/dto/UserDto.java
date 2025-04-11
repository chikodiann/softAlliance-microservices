package com.chikodiann.employee_mgmt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "UserDto",
        description = "Schema representing basic user details"
)
public class UserDto {

    @Schema(description = "User's first name", example = "Chikodi")
    private String firstName;

    @Schema(description = "User's last name", example = "Anyanwu")
    private String lastName;

    @Schema(description = "User's email address", example = "chika@example.com")
    private String email;

    @Schema(description = "User's phone number", example = "08012345678")
    private String phoneNumber;
}
