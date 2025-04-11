package com.codesofscar.employee_mgmt.service;

import com.codesofscar.employee_mgmt.dto.EmployeesDTO;
import com.codesofscar.employee_mgmt.dto.UserDto;


public interface EmployeesService {

//    String logInEmployee(LoginDto userDto);
//    Employees saveEmployee(SignUpDto signupDto);
//    Employees saveAdmin(SignUpDto signupDto);

    UserDto getEmployeeById(Long id);

    boolean updateEmployee(Long id, UserDto employeeDTO);
    boolean deleteEmployee(Long id);
}
