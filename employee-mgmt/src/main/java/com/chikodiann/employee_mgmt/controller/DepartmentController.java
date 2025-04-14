package com.chikodiann.employee_mgmt.controller;

import com.chikodiann.employee_mgmt.constants.DepartmentConstants;
import com.chikodiann.employee_mgmt.dto.DepartmentDTO;
import com.chikodiann.employee_mgmt.dto.ErrorResponseDTO;
import com.chikodiann.employee_mgmt.dto.ResponseDTO;
import com.chikodiann.employee_mgmt.entity.Department;
import com.chikodiann.employee_mgmt.service.impl.DepartmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/department")
@Tag(
        name = "CRUD REST APIs for Department Management",
        description = "CRUD REST APIs to CREATE, FETCH, UPDATE and DELETE department"
)
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(summary = "Fetch Department REST API", description = "REST API to fetch employee")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping("/get-details/{departmentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<DepartmentDTO> viewDepartment(@PathVariable Long departmentId) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentDetails(departmentId);

        return ResponseEntity.status(HttpStatus.OK).body(departmentDTO);
    }

    @Operation(summary = "Admin Add Department REST API", description = "Allows admin to add a new department")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        Department savedDepartment = departmentService.saveDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }


    @Operation(summary = "Admin Update Department For Employee REST API", description = "REST API for admin to update department")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping("/update/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO,
                                                        @PathVariable Long departmentId) {
        boolean isUpdated = departmentService.updateDepartment(departmentId, departmentDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(DepartmentConstants.STATUS_200, DepartmentConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(DepartmentConstants.STATUS_417, DepartmentConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Admin Delete Department REST API", description = "Allows admin to delete a department by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping("/delete/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteDepartment(@PathVariable Long departmentId) {
        boolean isDeleted = departmentService.deleteDepartment(departmentId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(DepartmentConstants.STATUS_200, DepartmentConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(DepartmentConstants.STATUS_417, DepartmentConstants.MESSAGE_417_UPDATE));
        }
    }
}
