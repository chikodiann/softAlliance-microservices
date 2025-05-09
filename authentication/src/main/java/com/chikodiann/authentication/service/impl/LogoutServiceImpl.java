package com.chikodiann.authentication.service.impl;

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
        // Check if there is an active session and invalidate it
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
            log.info("Session invalidated for user: {}",
                    authentication != null ? authentication.getName() : "Unknown");
        } else {
            log.warn("Attempted logout with no session available");
        }

        // Clear the SecurityContext to remove any authentication info
        SecurityContextHolder.clearContext();
        log.info("Security context cleared for user: {}",
                authentication != null ? authentication.getName() : "Unknown");
    }
}
