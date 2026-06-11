package com.sales.order_service.dto;

import java.math.BigDecimal;

public class OrderCreatedEvent {

    private Long orderId;
    private Long productId;
    private Long customerId;
    private Integer quantity;
    private BigDecimal totalAmount;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(Long orderId, Long productId, Long customerId, Integer quantity, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
