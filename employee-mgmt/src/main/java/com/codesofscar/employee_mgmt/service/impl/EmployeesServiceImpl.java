package com.codesofscar.employee_mgmt.service.impl;

import com.codesofscar.employee_mgmt.dto.*;
import com.codesofscar.employee_mgmt.entity.Employees;
import com.codesofscar.employee_mgmt.exception.*;
import com.codesofscar.employee_mgmt.feign.EmployeeInterface;
import com.codesofscar.employee_mgmt.mapper.EmployeeMapper;
import com.codesofscar.employee_mgmt.repository.DepartmentRepository;
import com.codesofscar.employee_mgmt.repository.EmployeesRepository;
import com.codesofscar.employee_mgmt.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j

public class EmployeesServiceImpl implements EmployeesService {

    private EmployeesRepository employeesRepository;
    private EmployeeInterface employeeInterface;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeesServiceImpl(EmployeesRepository employeesRepository, DepartmentRepository departmentRepository, EmployeeInterface employeeInterface) {
        this.employeesRepository = employeesRepository;
        this.departmentRepository = departmentRepository;
        this.employeeInterface = employeeInterface;
    }

    @Override

    public UserDto getEmployeeById(Long id) {
        UserDto employeeDto = employeeInterface.getUserForEmployee(id).getBody();
        return employeeDto;
    }


    @Override
    public boolean updateEmployee(Long id,  UserDto employeeDto) {
        boolean isUpdated = false;

        String updatedUser = employeeInterface.updateUserForEmployee(id, employeeDto).getBody();

        return isUpdated;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        Employees employees = employeesRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee not found"));
        employeesRepository.delete(employees);
        return true;
    }






}

