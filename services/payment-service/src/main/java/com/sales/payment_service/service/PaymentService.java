package com.sales.payment_service.service;

import com.sales.payment_service.entity.Payment;
import com.sales.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<Payment> findAll() {
        return repository.findAll();
    }

    public Payment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
    }

    public List<Payment> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    public Payment save(Payment payment) {
        if (payment.getStatus() == null || payment.getStatus().isBlank()) {
            payment.setStatus("PENDING");
        }

        return repository.save(payment);
    }

    public Payment approve(Long id) {
        Payment payment = findById(id);
        payment.setStatus("APPROVED");
        return repository.save(payment);
    }

    public Payment reject(Long id) {
        Payment payment = findById(id);
        payment.setStatus("REJECTED");
        return repository.save(payment);
    }

    public Payment updateStatus(Long id, String status) {
        Payment payment = findById(id);
        payment.setStatus(status);
        return repository.save(payment);
    }

    public void delete(Long id) {
        Payment payment = findById(id);
        repository.delete(payment);
    }
}
