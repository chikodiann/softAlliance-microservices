package com.codesofscar.employee_mgmt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @Id
    private Long userId;

    private Long departmentId;


}
