package com.chikodiann.employee_mgmt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "LoginDto",
        description = "Schema for employee login credentials"
)
public class LoginDto {
    private String email;
    private String password;
}

