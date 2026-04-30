package com.sales.customer_service.service;

import com.sales.customer_service.entity.Customer;
import com.sales.customer_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Long id, Customer customer) {
        Customer existing = findById(id);

        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        existing.setAddress(customer.getAddress());
        existing.setStatus(customer.getStatus());

        return repository.save(existing);
    }

    public void delete(Long id) {
        Customer existing = findById(id);
        repository.delete(existing);
    }
}