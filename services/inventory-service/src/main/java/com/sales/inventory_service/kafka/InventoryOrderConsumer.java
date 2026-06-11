package com.sales.inventory_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryOrderConsumer {

    @KafkaListener(topics = "order-created", groupId = "inventory-service-group")
    public void listenOrderCreated(String message) {
        System.out.println("INVENTORY-SERVICE recibió evento order-created: " + message);
        System.out.println("Simulando reserva/descuento de stock...");
    }
}
