package com.ms.ecommerce.service;

import com.ms.ecommerce.grpc.Customer;
import com.ms.ecommerce.grpc.CustomerServiceGrpc;
import com.ms.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceGrpcImpl extends CustomerServiceGrpc.CustomerServiceImplBase {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void findCustomerById(Customer.CustomerRequest request, io.grpc.stub.StreamObserver<Customer.CustomerResponse> responseObserver) {
        String customerId = request.getId();
        Optional<com.ms.ecommerce.entity.Customer> customerEntityOptional = repository.findById(Long.parseLong(customerId));
        if (customerEntityOptional.isPresent()) {
            com.ms.ecommerce.entity.Customer customerEntity = customerEntityOptional.get();
            Customer.CustomerResponse response = Customer.CustomerResponse.newBuilder()
                    .setId(customerId)
                    .setName(customerEntity.getName())
                    .setEmail(customerEntity.getEmail())
                    .build();
            responseObserver.onNext(response);
        } else {
            responseObserver.onNext(null);
        }
        responseObserver.onCompleted();
    }
}
