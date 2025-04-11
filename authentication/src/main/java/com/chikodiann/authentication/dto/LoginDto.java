package com.chikodiann.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "For employee login "
)
public class LoginDto {
    private String email;
    private String password;
}

