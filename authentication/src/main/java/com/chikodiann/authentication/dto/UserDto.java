package com.chikodiann.authentication.dto;

import com.chikodiann.authentication.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "Base User", description = "Data transfer object for user details")
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Role role;
    private Long departmentId;
}

