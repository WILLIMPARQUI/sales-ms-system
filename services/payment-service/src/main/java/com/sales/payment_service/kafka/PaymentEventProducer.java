package com.sales.payment_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.payment_service.dto.PaymentCompletedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.kafka.topic.payment-completed}")
    private String paymentCompletedTopic;

    public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishPaymentCompleted(PaymentCompletedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(paymentCompletedTopic, String.valueOf(event.getOrderId()), message);
            System.out.println("PAYMENT-SERVICE envio evento payment-completed: " + message);
        } catch (Exception e) {
            throw new RuntimeException("Error enviando evento payment-completed", e);
        }
    }
}