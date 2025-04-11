package com.codesofscar.employee_mgmt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@Schema(
        name = "Base User",
        description = "Schema to fill base user details"
)
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public UserDto(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
