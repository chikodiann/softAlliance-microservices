package com.codesofscar.employee_mgmt.mapper;

import com.codesofscar.employee_mgmt.dto.DepartmentDTO;
import com.codesofscar.employee_mgmt.entity.Department;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DepartmentMapper {

    public static DepartmentDTO maptoDepartmentDTO(Department department, DepartmentDTO departmentDTO) {
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());
        return departmentDTO;
    }

    public static Department maptoDepartment(DepartmentDTO departmentDTO, Department department) {
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        return department;
    }

}