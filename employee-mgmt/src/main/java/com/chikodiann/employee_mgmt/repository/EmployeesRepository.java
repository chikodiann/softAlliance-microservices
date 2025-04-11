package com.chikodiann.employee_mgmt.repository;

import com.chikodiann.employee_mgmt.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    List<Employees> findByDepartmentId(Long departmentId);

}

