package com.sales.order_service.service;

import com.sales.order_service.entity.Order;
import com.sales.order_service.entity.OrderItem;
import com.sales.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    public List<Order> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public Order save(Order order) {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            BigDecimal subtotal = item.getUnitPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            item.setSubtotal(subtotal);
            item.setOrder(order);
            total = total.add(subtotal);
        }

        order.setTotal(total);

        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("CREATED");
        }

        return repository.save(order);
    }

    public Order updateStatus(Long id, String status) {
        Order existing = findById(id);
        existing.setStatus(status);
        return repository.save(existing);
    }

    public void delete(Long id) {
        Order existing = findById(id);
        repository.delete(existing);
    }
}