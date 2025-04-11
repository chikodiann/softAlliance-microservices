package com.codesofscar.employee_mgmt.controller;

import com.codesofscar.employee_mgmt.constants.DepartmentConstants;
import com.codesofscar.employee_mgmt.dto.DepartmentDTO;
import com.codesofscar.employee_mgmt.dto.ErrorResponseDTO;
import com.codesofscar.employee_mgmt.dto.ResponseDTO;
import com.codesofscar.employee_mgmt.entity.Department;
import com.codesofscar.employee_mgmt.service.impl.DepartmentServiceImpl;
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
import org.springframework.web.bind.annotation.*;

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

    @Operation(
            summary = "Fetch Department REST API",
            description = "REST API to fetch employee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
    @GetMapping("/get-details/{departmentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<DepartmentDTO> viewDepartment(@PathVariable Long departmentId) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentDetails(departmentId);

        return ResponseEntity.status(HttpStatus.OK).body(departmentDTO);
    }

    @Operation(
            summary = "Admin Add Department For Employee REST API",
            description = "REST API for admin to department details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department savedDepartment = departmentService.saveDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDTO);
    }


    @Operation(
            summary = "Admin Update Department For Employee REST API",
            description = "REST API for admin to update department"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
    @PutMapping("/update/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO,
                                                        @PathVariable Long departmentId) {
        Boolean isUpdated = departmentService.updateDepartment(departmentId, departmentDTO);
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

    @Operation(
            summary = "Admin Delete User For Employee REST API",
            description = "REST API for admin to delete employee user details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
    @DeleteMapping("/delete/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteDepartment(@PathVariable Long userId) {
        Boolean isDeleted = departmentService.deleteDepartment(userId);
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
