package com.sales.inventory_service.service;

import com.sales.inventory_service.entity.Inventory;
import com.sales.inventory_service.entity.InventoryMovement;
import com.sales.inventory_service.repository.InventoryMovementRepository;
import com.sales.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository movementRepository;

    public InventoryService(InventoryRepository inventoryRepository, InventoryMovementRepository movementRepository) {
        this.inventoryRepository = inventoryRepository;
        this.movementRepository = movementRepository;
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public Inventory findById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
    }

    public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el producto ID: " + productId));
    }

    public Inventory save(Inventory inventory) {
        if (inventoryRepository.existsByProductId(inventory.getProductId())) {
            throw new RuntimeException("Ya existe inventario para el producto ID: " + inventory.getProductId());
        }

        if (inventory.getStatus() == null || inventory.getStatus().isBlank()) {
            inventory.setStatus("ACTIVE");
        }

        return inventoryRepository.save(inventory);
    }

    public Inventory addEntry(Long productId, Integer quantity, String description) {
        Inventory inventory = findByProductId(productId);
        inventory.setCurrentStock(inventory.getCurrentStock() + quantity);

        InventoryMovement movement = new InventoryMovement();
        movement.setProductId(productId);
        movement.setMovementType("ENTRY");
        movement.setQuantity(quantity);
        movement.setDescription(description);

        movementRepository.save(movement);
        return inventoryRepository.save(inventory);
    }

    public Inventory addExit(Long productId, Integer quantity, String description) {
        Inventory inventory = findByProductId(productId);

        if (inventory.getCurrentStock() < quantity) {
            throw new RuntimeException("Stock insuficiente para el producto ID: " + productId);
        }

        inventory.setCurrentStock(inventory.getCurrentStock() - quantity);

        InventoryMovement movement = new InventoryMovement();
        movement.setProductId(productId);
        movement.setMovementType("EXIT");
        movement.setQuantity(quantity);
        movement.setDescription(description);

        movementRepository.save(movement);
        return inventoryRepository.save(inventory);
    }

    public Inventory reserveStock(Long productId, Integer quantity) {
        return addExit(productId, quantity, "Reserva de stock para pedido");
    }

    public Inventory releaseStock(Long productId, Integer quantity) {
        return addEntry(productId, quantity, "Liberación de stock por cancelación");
    }

    public List<InventoryMovement> findMovementsByProductId(Long productId) {
        return movementRepository.findByProductId(productId);
    }

    public void delete(Long id) {
        Inventory inventory = findById(id);
        inventoryRepository.delete(inventory);
    }
}