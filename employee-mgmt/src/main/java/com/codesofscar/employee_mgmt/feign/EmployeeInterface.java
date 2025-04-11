package com.codesofscar.employee_mgmt.feign;

import com.codesofscar.employee_mgmt.dto.SignUpDto;
import com.codesofscar.employee_mgmt.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("AUTHENTICATION")
public interface EmployeeInterface {
    @PostMapping("/auth/generate-new-user")
    public ResponseEntity<String> getNewUserForEmployee(@RequestBody SignUpDto signupDto, final HttpServletRequest request);

    @GetMapping("/auth/generate-user")
    public ResponseEntity<UserDto> getUserForEmployee(Long userId);

    @PostMapping("/auth/update-user/{userId}")
    public ResponseEntity<String> updateUserForEmployee(@PathVariable Long userId, @RequestBody UserDto userDto);

    @PostMapping("/auth/delete-user/{userId}")
    public ResponseEntity<String> deleteUserForEmployee(@PathVariable Long userId);
}
