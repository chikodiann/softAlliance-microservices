package com.chikodiann.employee_mgmt.service;

import com.chikodiann.employee_mgmt.dto.UserDto;


public interface EmployeesService {

    UserDto getEmployeeById(Long id);

    boolean updateEmployee(Long id, UserDto employeeDTO);
    boolean deleteEmployee(Long id);
}
