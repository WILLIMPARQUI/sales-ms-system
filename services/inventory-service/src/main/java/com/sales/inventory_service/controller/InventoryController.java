package com.sales.inventory_service.controller;

import com.sales.inventory_service.entity.Inventory;
import com.sales.inventory_service.entity.InventoryMovement;
import com.sales.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService service;

    @Value("${server.port}")
    private String port;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inventory> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Inventory findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/product/{productId}")
    public Inventory findByProductId(@PathVariable Long productId) {
        return service.findByProductId(productId);
    }

    @PostMapping
    public Inventory save(@Valid @RequestBody Inventory inventory) {
        return service.save(inventory);
    }

    @PostMapping("/entry")
    public Inventory entry(@RequestBody Map<String, String> body) {
        Long productId = Long.valueOf(body.get("productId"));
        Integer quantity = Integer.valueOf(body.get("quantity"));
        String description = body.getOrDefault("description", "Entrada de stock");
        return service.addEntry(productId, quantity, description);
    }

    @PostMapping("/exit")
    public Inventory exit(@RequestBody Map<String, String> body) {
        Long productId = Long.valueOf(body.get("productId"));
        Integer quantity = Integer.valueOf(body.get("quantity"));
        String description = body.getOrDefault("description", "Salida de stock");
        return service.addExit(productId, quantity, description);
    }

    @PostMapping("/reserve")
    public Inventory reserve(@RequestBody Map<String, String> body) {
        Long productId = Long.valueOf(body.get("productId"));
        Integer quantity = Integer.valueOf(body.get("quantity"));
        return service.reserveStock(productId, quantity);
    }

    @PostMapping("/release")
    public Inventory release(@RequestBody Map<String, String> body) {
        Long productId = Long.valueOf(body.get("productId"));
        Integer quantity = Integer.valueOf(body.get("quantity"));
        return service.releaseStock(productId, quantity);
    }

    @GetMapping("/product/{productId}/movements")
    public List<InventoryMovement> movements(@PathVariable Long productId) {
        return service.findMovementsByProductId(productId);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Inventario eliminado correctamente");
    }

    @GetMapping("/instance")
    public Map<String, String> instance() {
        return Map.of(
                "service", "inventory-service",
                "port", port,
                "message", "Instancia activa"
        );
    }
}