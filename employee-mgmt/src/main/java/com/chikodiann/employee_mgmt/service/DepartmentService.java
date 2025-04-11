package com.chikodiann.employee_mgmt.service;

import com.chikodiann.employee_mgmt.dto.EmployeesDTO;
import com.chikodiann.employee_mgmt.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    List<EmployeesDTO> getEmployeesByDepartment(Long departmentId);


    boolean updateDepartment(Long id, DepartmentDTO departmentDTO);

    boolean deleteDepartment(Long id);
}
