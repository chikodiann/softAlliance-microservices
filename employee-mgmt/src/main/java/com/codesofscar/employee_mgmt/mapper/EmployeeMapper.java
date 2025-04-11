package com.codesofscar.employee_mgmt.mapper;

import com.codesofscar.employee_mgmt.dto.EmployeesDTO;
import com.codesofscar.employee_mgmt.entity.Employees;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmployeeMapper {

    public static EmployeesDTO mapToEmployeeDto(Employees employee, EmployeesDTO employeeDTO) {
        employeeDTO.setUserId(employee.getUserId());
        employeeDTO.setDepartmentId(employee.getDepartmentId());
        return employeeDTO;
    }

    public static Employees mapToEmployee(EmployeesDTO employeeDTO, Employees employee) {
        employee.setUserId(employeeDTO.getUserId());
        employee.setDepartmentId(employeeDTO.getDepartmentId());
        return employee;
    }
}