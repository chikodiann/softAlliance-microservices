package com.chikodiann.employee_mgmt.feign;

import com.chikodiann.employee_mgmt.dto.SignUpDto;
import com.chikodiann.employee_mgmt.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "AUTHENTICATION")
public interface EmployeeInterface {

    @PostMapping("/auth/generate-new-user")
    ResponseEntity<String> getNewUserForEmployee(@RequestBody SignUpDto signupDto);

    @GetMapping("/auth/generate-user/{userId}")
    ResponseEntity<UserDto> getUserForEmployee(@PathVariable("userId") Long userId);

    @PutMapping("/auth/update-user/{userId}")
    ResponseEntity<String> updateUserForEmployee(@PathVariable("userId") Long userId, @RequestBody UserDto userDto);

    @DeleteMapping("/auth/delete-user/{userId}")
    ResponseEntity<String> deleteUserForEmployee(@PathVariable("userId") Long userId);
}
