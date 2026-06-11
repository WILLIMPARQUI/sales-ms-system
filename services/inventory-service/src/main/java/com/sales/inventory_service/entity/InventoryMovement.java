package com.sales.inventory_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del producto es obligatorio")
    @Column(nullable = false)
    private Long productId;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Column(nullable = false, length = 20)
    private String movementType;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime movementDate;

    @Column(length = 200)
    private String description;

    public InventoryMovement() {
    }

    public InventoryMovement(Long id, Long productId, String movementType, Integer quantity, LocalDateTime movementDate, String description) {
        this.id = id;
        this.productId = productId;
        this.movementType = movementType;
        this.quantity = quantity;
        this.movementDate = movementDate;
        this.description = description;
    }

    @PrePersist
    public void prePersist() {
        if (movementDate == null) {
            movementDate = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getMovementType() {
        return movementType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}