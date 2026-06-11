package com.sales.auth_service.service;

import com.sales.auth_service.dto.AuthResponse;
import com.sales.auth_service.dto.LoginRequest;
import com.sales.auth_service.dto.RegisterRequest;
import com.sales.auth_service.entity.User;
import com.sales.auth_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public AuthResponse register(RegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe: " + request.getUsername());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setStatus("ACTIVE");

        User saved = repository.save(user);

        return new AuthResponse(
                "Usuario registrado correctamente",
                saved.getUsername(),
                saved.getRole(),
                saved.getStatus()
        );
    }

    public AuthResponse login(LoginRequest request) {
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("Usuario inactivo");
        }

        return new AuthResponse(
                "Login correcto",
                user.getUsername(),
                user.getRole(),
                user.getStatus()
        );
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }
}