package com.sales.auth_service.dto;

public class AuthResponse {

    private String message;
    private String username;
    private String role;
    private String status;

    public AuthResponse() {
    }

    public AuthResponse(String message, String username, String role, String status) {
        this.message = message;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
