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
        UserDto employeeDto = employeeInterface.getUserForEmployee(id).getBody();
        if (employeeDto != null) {
            log.info("Fetched employee details: {}", employeeDto);
        } else {
            log.warn("Employee with ID {} not found in the external service", id);
        }
        return employeeDto;
    }

    @Override
    public boolean updateEmployee(Long id, UserDto employeeDto) {
        log.info("Updating employee with ID: {}", id);
        try {
            String response = employeeInterface.updateUserForEmployee(id, employeeDto).getBody();
            if (response != null && response.toLowerCase().contains("success")) {
                log.info("Successfully updated employee with ID: {}", id);
                return true;
            } else {
                log.error("Failed to update employee with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            log.error("Error occurred while updating employee with ID: {}: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);
        Employees employee = employeesRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee with ID {} not found", id);
                    return new UserNotFoundException("Employee not found");
                });
        employeesRepository.delete(employee);
        log.info("Successfully deleted employee with ID: {}", id);
        try {
            employeeInterface.deleteUserForEmployee(id);
            log.info("Successfully deleted employee from external service with ID: {}", id);
            return true;
        } catch (Exception e) {
            log.error("Error occurred while deleting employee from external service with ID: {}: {}", id, e.getMessage());
            return false;
        }
    }
}
