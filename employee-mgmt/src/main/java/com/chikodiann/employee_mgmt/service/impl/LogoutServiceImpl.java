package com.chikodiann.employee_mgmt.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutServiceImpl implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalidate the session
            session.invalidate();
            log.info("Session invalidated for user: {}", authentication != null ? authentication.getName() : "Unknown");
        } else {
            log.warn("No session found for user: {}", authentication != null ? authentication.getName() : "Unknown");
        }

        // Clear the security context
        SecurityContextHolder.clearContext();
        log.info("Security context cleared for user: {}", authentication != null ? authentication.getName() : "Unknown");

        // Log the successful logout event
        log.info("Logout successful for user: {}", authentication != null ? authentication.getName() : "Unknown");
    }
}
