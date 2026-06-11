package com.sales.order_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.order_service.dto.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.kafka.topic.order-created}")
    private String orderCreatedTopic;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(orderCreatedTopic, String.valueOf(event.getOrderId()), message);
            System.out.println("Evento enviado a Kafka: " + message);
        } catch (Exception e) {
            throw new RuntimeException("Error enviando evento order-created", e);
        }
    }
}
