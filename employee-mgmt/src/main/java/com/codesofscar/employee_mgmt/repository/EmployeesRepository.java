package com.codesofscar.employee_mgmt.repository;

import com.codesofscar.employee_mgmt.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    List<Employees> findByDepartmentId(Long departmentId);

}

