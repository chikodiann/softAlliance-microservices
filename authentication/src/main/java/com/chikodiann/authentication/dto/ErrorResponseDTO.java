package com.chikodiann.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Standard format for all error responses"
)
public class ErrorResponseDTO {

    @Schema(
            description = "The exact endpoint that was called when the error occurred",
            example = "/api/v1/auth/login"
    )
    private  String apiPath;

    @Schema(
            description = "HTTP status code of the error",
            example = "400"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "A short message explaining what went wrong",
            example = "Invalid email or password"
    )
    private  String errorMessage;

    @Schema(
            description = "The date and time when the error occurred (in ISO 8601 format)",
            example = "2025-04-11T16:25:43"
    )
    private LocalDateTime errorTime;

}
