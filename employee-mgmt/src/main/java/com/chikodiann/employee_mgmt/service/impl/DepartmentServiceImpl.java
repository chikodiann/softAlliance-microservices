package com.chikodiann.employee_mgmt.service.impl;

import com.chikodiann.employee_mgmt.dto.EmployeesDTO;
import com.chikodiann.employee_mgmt.dto.DepartmentDTO;
import com.chikodiann.employee_mgmt.entity.Department;
import com.chikodiann.employee_mgmt.entity.Employees;
import com.chikodiann.employee_mgmt.exception.DepartmentAlreadyExistsException;
import com.chikodiann.employee_mgmt.exception.DepartmentNotFoundException;
import com.chikodiann.employee_mgmt.exception.ResourceNotFoundException;
import com.chikodiann.employee_mgmt.mapper.DepartmentMapper;
import com.chikodiann.employee_mgmt.mapper.EmployeeMapper;
import com.chikodiann.employee_mgmt.repository.DepartmentRepository;
import com.chikodiann.employee_mgmt.repository.EmployeesRepository;
import com.chikodiann.employee_mgmt.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeesRepository employeesRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeesRepository employeesRepository) {
        this.departmentRepository = departmentRepository;
        this.employeesRepository = employeesRepository;
    }

    @Override
    public List<EmployeesDTO> getEmployeesByDepartment(Long departmentId) {
        log.info("Fetching employees for department ID: {}", departmentId);
        List<Employees> departmentGroup = employeesRepository.findByDepartmentId(departmentId);
        List<EmployeesDTO> employeesDTOList = new ArrayList<>();

        for (Employees employee : departmentGroup) {
            EmployeesDTO employeeDTO = EmployeeMapper.mapToEmployeeDto(employee, new EmployeesDTO());
            employeesDTOList.add(employeeDTO);
        }
        return employeesDTOList;
    }

    public DepartmentDTO getDepartmentDetails(Long departmentId) {
        log.info("Fetching department details for ID: {}", departmentId);
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", departmentId.toString()));
        return DepartmentMapper.maptoDepartmentDTO(department, new DepartmentDTO());
    }

    public Department saveDepartment(DepartmentDTO departmentDTO) {
        log.info("Saving new department: {}", departmentDTO.getName());
        if (departmentRepository.existsByName(departmentDTO.getName())) {
            throw new DepartmentAlreadyExistsException("Department already exists, please create a different one" );
        }
        Department department = new Department();

        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        return departmentRepository.save(department);
    }

    @Override
    public boolean updateDepartment(Long id, DepartmentDTO departmentDTO) {
        log.info("Updating department ID: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not Found"));

            department.setName(departmentDTO.getName());
            department.setDescription(departmentDTO.getDescription());

            departmentRepository.save(department);
        return true;
    }

    @Override
    public boolean deleteDepartment(Long id) {
        log.info("Deleting department ID: {}", id);
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        departmentRepository.delete(department);
        return true;
    }
}
