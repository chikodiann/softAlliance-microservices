package com.chikodiann.employee_mgmt.constants;

public class EmployeeConstants {

    private EmployeeConstants() {
        // Prevent instantiation
    }

    // HTTP Status Codes
    public static final String STATUS_200 = "200";
    public static final String STATUS_201 = "201";
    public static final String STATUS_417 = "417";
    public static final String STATUS_500 = "500";

    // Success Messages
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String MESSAGE_201 = "Account created successfully";

    // Failure Messages
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact the Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact the Dev team";
    public static final String MESSAGE_500 = "Internal Server Error";
}
