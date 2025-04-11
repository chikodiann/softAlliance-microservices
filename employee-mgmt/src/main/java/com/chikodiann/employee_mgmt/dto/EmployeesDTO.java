package com.chikodiann.employee_mgmt.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(
        name = "EmployeesDTO",
        description = "DTO to represent basic employee and department linkage information"
)
@Data
public class EmployeesDTO {
    private Long userId;
    private Long departmentId;
}

