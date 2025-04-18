package com.chikodiann.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotEligibleException extends RuntimeException {
    public UserNotEligibleException(String message) {}
}
