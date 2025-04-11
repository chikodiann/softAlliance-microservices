package com.codesofscar.authentication.dto;

import com.codesofscar.authentication.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
        name = "Base User",
        description = "Schema to fill base user details"
)
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Role role;
    private Long departmentId;
}

