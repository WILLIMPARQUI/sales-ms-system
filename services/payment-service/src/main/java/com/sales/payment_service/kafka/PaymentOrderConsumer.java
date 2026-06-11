package com.sales.payment_service.kafka;

import com.sales.payment_service.dto.PaymentCompletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentOrderConsumer {

    private final PaymentEventProducer paymentEventProducer;

    public PaymentOrderConsumer(PaymentEventProducer paymentEventProducer) {
        this.paymentEventProducer = paymentEventProducer;
    }

    @KafkaListener(topics = "order-created", groupId = "payment-service-group")
    public void listenOrderCreated(String message) {
        System.out.println("PAYMENT-SERVICE recibio evento order-created: " + message);
        System.out.println("Procesando pago del pedido...");

        PaymentCompletedEvent event = new PaymentCompletedEvent(
                1L,
                "PAID",
                "Pago procesado correctamente"
        );

        paymentEventProducer.publishPaymentCompleted(event);
    }
}