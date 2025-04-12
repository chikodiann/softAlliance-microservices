package com.chikodiann.employee_mgmt.controller;

import com.chikodiann.employee_mgmt.dto.EmployeesDTO;
import com.chikodiann.employee_mgmt.dto.ErrorResponseDTO;
import com.chikodiann.employee_mgmt.dto.ResponseDTO;
import com.chikodiann.employee_mgmt.dto.UserDto;
import com.chikodiann.employee_mgmt.feign.EmployeeInterface;
import com.chikodiann.employee_mgmt.service.impl.DepartmentServiceImpl;
import com.chikodiann.employee_mgmt.service.impl.EmployeesServiceImpl;
import com.chikodiann.employee_mgmt.constants.EmployeeConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(
        name = "CRUD REST APIs for Employee Management",
        description = "CRUD REST APIs to CREATE, FETCH, UPDATE and DELETE employees"
)
public class EmployeesController {
    private final EmployeesServiceImpl userService;
    private final DepartmentServiceImpl departmentService;
    private final EmployeeInterface employeeInterface;

    @Autowired
    public EmployeesController(EmployeesServiceImpl userService, DepartmentServiceImpl departmentService, EmployeeInterface employeeInterface) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.employeeInterface = employeeInterface;
    }

    @Operation(
            summary = "Admin Update User For Employee REST API",
            description = "REST API for admin to update employee user details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PutMapping("/update-employee/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateEmployee(@RequestBody UserDto employeeDTO,
                                                      @PathVariable Long userId) {
        Boolean isUpdated = userService.updateEmployee(userId, employeeDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(EmployeeConstants.STATUS_200, EmployeeConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(EmployeeConstants.STATUS_417, EmployeeConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Admin Delete User For Employee REST API",
            description = "REST API for admin to delete user employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )

    @DeleteMapping("/delete-employee/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable Long userId) {
        boolean isDeleted = userService.deleteEmployee(userId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(EmployeeConstants.STATUS_200, EmployeeConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(EmployeeConstants.STATUS_417, EmployeeConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "View Employee REST API",
            description = "REST API for admin to delete user employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/get-employee/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> viewEmployee(@PathVariable Long userId) {
        UserDto employeeDTO = userService.getEmployeeById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @Operation(
            summary = "View Employees by Department REST API",
            description = "Allows Admin or Manager to fetch employees in a specific department"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employees retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            )
    })
    @GetMapping("/get-department-employees/{departmentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<EmployeesDTO>> viewEmployeesByDepartment(@PathVariable Long departmentId) {
        List<EmployeesDTO> employeesByDepartment = departmentService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(employeesByDepartment);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<UserDto> viewOwnDetails(Authentication authentication) {
        String email = authentication.getName();
        UserDto user = employeeInterface.getUserForEmployeeByEmail(email).getBody();
        return ResponseEntity.ok(user);
    }

}