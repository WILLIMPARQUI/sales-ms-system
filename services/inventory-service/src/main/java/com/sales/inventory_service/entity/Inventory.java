package com.sales.inventory_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del producto es obligatorio")
    @Column(nullable = false, unique = true)
    private Long productId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(nullable = false, length = 120)
    private String productName;

    @NotNull(message = "El stock actual es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer currentStock;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    @Column(nullable = false)
    private Integer minimumStock;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    private String status;

    public Inventory() {
    }

    public Inventory(Long id, Long productId, String productName, Integer currentStock, Integer minimumStock, String status) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}