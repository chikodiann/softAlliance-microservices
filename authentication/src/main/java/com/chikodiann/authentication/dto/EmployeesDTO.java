package com.chikodiann.authentication.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "EmployeeDto", description = "Basic employee information")
@Data
public class EmployeesDTO {
    private Long userId;
    private Long departmentId;
}

