package com.codesofscar.employee_mgmt.service.impl;

import com.codesofscar.employee_mgmt.dto.DepartmentDTO;
import com.codesofscar.employee_mgmt.dto.EmployeesDTO;
import com.codesofscar.employee_mgmt.entity.Department;
import com.codesofscar.employee_mgmt.entity.Employees;
import com.codesofscar.employee_mgmt.exception.DepartmentAlreadyExistsException;
import com.codesofscar.employee_mgmt.exception.DepartmentNotFoundException;
import com.codesofscar.employee_mgmt.exception.ResourceNotFoundException;
import com.codesofscar.employee_mgmt.mapper.DepartmentMapper;
import com.codesofscar.employee_mgmt.mapper.EmployeeMapper;
import com.codesofscar.employee_mgmt.repository.DepartmentRepository;
import com.codesofscar.employee_mgmt.repository.EmployeesRepository;
import com.codesofscar.employee_mgmt.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        List<Employees> departmentGroup = employeesRepository.findByDepartmentId(departmentId);
        List<EmployeesDTO> employeesDTOList = new ArrayList<>();

        for (Employees employee : departmentGroup) {
            EmployeesDTO employeeDTO = EmployeeMapper.mapToEmployeeDto(employee, new EmployeesDTO());
            employeesDTOList.add(employeeDTO);
        }
        return employeesDTOList;
    }

    public DepartmentDTO getDepartmentDetails(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", departmentId.toString()));
        DepartmentDTO departmentDTO = DepartmentMapper.maptoDepartmentDTO(department, new DepartmentDTO());
        return departmentDTO;
    }

    public Department saveDepartment(DepartmentDTO departmentDTO) {


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
        boolean isUpdated = false;


        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not Found"));


        if (departmentDTO != null) {

            department.setName(departmentDTO.getName());
            department.setDescription(departmentDTO.getDescription());

            departmentRepository.save(department);

            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        departmentRepository.delete(department);
        return true;
    }
}
