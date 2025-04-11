package com.codesofscar.authentication.entity;

import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employees extends Users {


    private Long departmentId;


}
