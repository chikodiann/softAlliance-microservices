package com.chikodiann.employee_mgmt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponseDTO",
        description = "Schema representing details of an error response"
)
public class ErrorResponseDTO {

    @Schema(
            description = "API path invoked by the client",
            example = "/api/employee/get-employee/5"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code returned",
            example = "404"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Detailed error message",
            example = "Employee not found with id: 5"
    )
    private String errorMessage;

    @Schema(
            description = "Timestamp when the error occurred (ISO format)",
            example = "2025-04-11T14:30:00"
    )
    private LocalDateTime errorTime;
}
