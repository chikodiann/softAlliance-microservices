package com.chikodiann.employee_mgmt.service.impl;

import com.chikodiann.employee_mgmt.dto.UserDto;
import com.chikodiann.employee_mgmt.entity.Employees;
import com.chikodiann.employee_mgmt.exception.UserNotFoundException;
import com.chikodiann.employee_mgmt.feign.EmployeeInterface;
import com.chikodiann.employee_mgmt.repository.DepartmentRepository;
import com.chikodiann.employee_mgmt.repository.EmployeesRepository;
import com.chikodiann.employee_mgmt.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeInterface employeeInterface;

    @Autowired
    public EmployeesServiceImpl(EmployeesRepository employeesRepository,
                                DepartmentRepository departmentRepository,
                                EmployeeInterface employeeInterface) {
        this.employeesRepository = employeesRepository;
        this.departmentRepository = departmentRepository;
        this.employeeInterface = employeeInterface;
    }

    @Override
    public UserDto getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeInterface.getUserForEmployee(id).getBody();
    }

    @Override
    public boolean updateEmployee(Long id, UserDto employeeDto) {
        log.info("Updating employee with ID: {}", id);
        String response = employeeInterface.updateUserForEmployee(id, employeeDto).getBody();
        return response != null && response.toLowerCase().contains("success");
    }

    @Override
    public boolean deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        Employees employees = employeesRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not found"));
        employeesRepository.delete(employees);
        employeeInterface.deleteUserForEmployee(id);
        return true;
    }
}
