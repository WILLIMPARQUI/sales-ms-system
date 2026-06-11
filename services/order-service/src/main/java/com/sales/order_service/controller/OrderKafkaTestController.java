package com.sales.order_service.controller;

import com.sales.order_service.dto.OrderCreatedEvent;
import com.sales.order_service.kafka.OrderEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderKafkaTestController {

    private final OrderEventProducer orderEventProducer;

    public OrderKafkaTestController(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @PostMapping("/demo")
    public ResponseEntity<?> createDemoOrder() {
        OrderCreatedEvent event = new OrderCreatedEvent(
                1L,
                100L,
                50L,
                2,
                new BigDecimal("250.00")
        );

        orderEventProducer.publishOrderCreated(event);

        return ResponseEntity.ok(Map.of(
                "message", "Pedido demo creado y evento enviado a Kafka",
                "topic", "order-created",
                "event", event
        ));
    }
}
