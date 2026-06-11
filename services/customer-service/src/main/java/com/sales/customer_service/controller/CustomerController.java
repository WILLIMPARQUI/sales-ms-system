package com.sales.customer_service.controller;

import com.sales.customer_service.entity.Customer;
import com.sales.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService service;

    @Value("${server.port}")
    private String port;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Customer save(@Valid @RequestBody Customer customer) {
        return service.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Cliente eliminado correctamente");
    }

    @GetMapping("/instance")
    public Map<String, String> instance() {
        return Map.of(
                "service", "customer-service",
                "port", port,
                "message", "Instancia activa"
        );
    }
}