package com.sales.auth_service.controller;

import com.sales.auth_service.dto.AuthResponse;
import com.sales.auth_service.dto.LoginRequest;
import com.sales.auth_service.dto.RegisterRequest;
import com.sales.auth_service.entity.User;
import com.sales.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthService service;

    @Value("${server.port}")
    private String port;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/api/v1/auth/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/api/v1/auth/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return service.login(request);
    }

    @GetMapping("/api/v1/users")
    public List<User> findAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping("/api/v1/users/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/api/v1/auth/instance")
    public Map<String, String> instance() {
        return Map.of(
                "service", "auth-service",
                "port", port,
                "message", "Instancia activa"
        );
    }
}