package com.hexagonalarch.adapters.controllers;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.usecases.Customer.CreateCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetAllCustomersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.IdentifyOrCreateCustomerUseCasePort;

import java.util.List;

public class CustomerController {

    private final CreateCustomerUseCasePort createCustomerUseCase;
    private final GetCustomerUseCasePort getCustomerUseCase;
    private final GetAllCustomersUseCasePort getAllCustomersUseCase;
    private final IdentifyOrCreateCustomerUseCasePort identifyOrCreateCustomerUseCase;

    public CustomerController(CreateCustomerUseCasePort createCustomerUseCase, GetCustomerUseCasePort getCustomerUseCase, GetAllCustomersUseCasePort getAllCustomersUseCase, IdentifyOrCreateCustomerUseCasePort identifyOrCreateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.identifyOrCreateCustomerUseCase = identifyOrCreateCustomerUseCase;
    }

    public Customer createCustomer(Customer customer) {
        return createCustomerUseCase.createCustomer(customer);
    }

    public Customer getCustomerById(Long id) {
        return getCustomerUseCase.getCustomerById(id);
    }

    public Customer identifyOrCreateCustomer(Customer customer) {
        return identifyOrCreateCustomerUseCase.identifyOrCreateCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return getAllCustomersUseCase.getAllCustomers();
    }
}