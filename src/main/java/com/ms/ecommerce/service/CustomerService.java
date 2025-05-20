package com.ms.ecommerce.service;

import com.ms.ecommerce.entity.Customer;
import com.ms.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = repository.save(customer);
        return new Customer()
                .setId(savedCustomer.getId())
                .setName(savedCustomer.getName())
                .setEmail(savedCustomer.getEmail());
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = repository.findAll();
        return customers.parallelStream()
                .map(customer -> new Customer()
                        .setId(customer.getId())
                        .setName(customer.getName())
                        .setEmail(customer.getEmail()))
                .toList();
    }

    public Customer getCustomer(long id) {
        Optional<Customer> customerOptional = repository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return new Customer().setId(customer.getId())
                    .setName(customer.getName())
                    .setEmail(customer.getEmail());
        }
        return null;
    }
}
