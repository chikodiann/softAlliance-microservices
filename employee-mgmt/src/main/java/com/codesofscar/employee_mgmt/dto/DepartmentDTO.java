package com.codesofscar.employee_mgmt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Department",
        description = "Schema to hold department information"
)
public class DepartmentDTO {
    private Long departmentId;
    private String name;
    private String description;
}

