package com.codesofscar.employee_mgmt.service;

import com.codesofscar.employee_mgmt.dto.DepartmentDTO;
import com.codesofscar.employee_mgmt.dto.EmployeesDTO;

import java.util.List;

public interface DepartmentService {

    List<EmployeesDTO> getEmployeesByDepartment(Long departmentId);


    boolean updateDepartment(Long id, DepartmentDTO departmentDTO);

    boolean deleteDepartment(Long id);
}
