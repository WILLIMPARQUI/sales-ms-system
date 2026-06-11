package com.sales.payment_service.dto;

public class PaymentCompletedEvent {

    private Long orderId;
    private String status;
    private String message;

    public PaymentCompletedEvent() {
    }

    public PaymentCompletedEvent(Long orderId, String status, String message) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}