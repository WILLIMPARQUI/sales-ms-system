package com.sales.payment_service.controller;

import com.sales.payment_service.entity.Payment;
import com.sales.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @Value("${server.port}")
    private String port;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Payment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Payment findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<Payment> findByOrderId(@PathVariable Long orderId) {
        return service.findByOrderId(orderId);
    }

    @PostMapping
    public Payment save(@Valid @RequestBody Payment payment) {
        return service.save(payment);
    }

    @PutMapping("/{id}/approve")
    public Payment approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public Payment reject(@PathVariable Long id) {
        return service.reject(id);
    }

    @PutMapping("/{id}/status")
    public Payment updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.updateStatus(id, body.get("status"));
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Pago eliminado correctamente");
    }

    @GetMapping("/instance")
    public Map<String, String> instance() {
        return Map.of(
                "service", "payment-service",
                "port", port,
                "message", "Instancia activa"
        );
    }
}