package com.sales.auth_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El usuario es obligatorio")
    @Column(nullable = false, unique = true, length = 80)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false, length = 120)
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    @Column(nullable = false, length = 30)
    private String role;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    private String status;

    public User() {
    }

    public User(Long id, String username, String password, String role, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}