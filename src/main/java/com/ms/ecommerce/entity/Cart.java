package com.ms.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Product> product;

    public Customer getCustomer() {
        return customer;
    }

    public Cart setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public List<Product> getProduct() {
        return product;
    }

    public Cart setProduct(List<Product> products) {
        this.product = products;
        return this;
    }
}
