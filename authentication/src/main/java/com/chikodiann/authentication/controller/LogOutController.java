package com.chikodiann.authentication.controller;

import com.chikodiann.authentication.service.impl.LogoutServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin
@RestController
@PreAuthorize("hasAnyRole()")
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@Tag(
        name = "CRUD REST APIs for Logging out",
        description = "REST APIs to logout users"
)
public class LogOutController {

    private final LogoutServiceImpl logoutService;


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutService.logout(request, response, authentication);
        return ResponseEntity.ok("Logout successful");
    }
}
