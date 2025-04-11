package com.codesofscar.employee_mgmt.repository;

import com.codesofscar.employee_mgmt.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional <Department> findByName (String name);

    boolean existsByName(String name);
}
