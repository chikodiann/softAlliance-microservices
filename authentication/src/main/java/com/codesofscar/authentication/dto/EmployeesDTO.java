package com.codesofscar.authentication.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold employee information"
)
@Data
public class EmployeesDTO {
    private Long userId;
    private Long departmentId;
}

