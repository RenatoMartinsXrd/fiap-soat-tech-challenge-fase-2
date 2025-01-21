package com.hexagonalarch.frameworks.rest.controllers;

import com.hexagonalarch.adapters.controllers.CustomerController;
import com.hexagonalarch.frameworks.rest.dto.request.CreateCustomerRequest;
import com.hexagonalarch.frameworks.rest.dto.response.CreateCustomerResponse;
import com.hexagonalarch.frameworks.rest.dto.response.GetCustomerResponse;
import com.hexagonalarch.adapters.presenters.GenericConverter;
import com.hexagonalarch.core.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerControllerRest {

    private final CustomerController customerController;
    private final GenericConverter genericConverter;

    public CustomerControllerRest(CustomerController customerController, GenericConverter genericConverter) {
        this.customerController = customerController;
        this.genericConverter = genericConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest customerRequest) {
        Customer customerInput = genericConverter.toDomain(customerRequest, Customer.class);
        Customer newCustomer = customerController.createCustomer(customerInput);
        return genericConverter.toDto(newCustomer, CreateCustomerResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerResponse getCustomerById(@PathVariable Long id) {
        Customer customer = customerController.getCustomerById(id);
        return genericConverter.toDto(customer, GetCustomerResponse.class);
    }

    @PostMapping("/identifyOrCreate")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerResponse identifyOrCreate(@Valid @RequestBody CreateCustomerRequest customerRequest) {
        Customer customerInput = genericConverter.toDomain(customerRequest, Customer.class);
        Customer customer = customerController.identifyOrCreateCustomer(customerInput);
        return genericConverter.toDto(customer, GetCustomerResponse.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetCustomerResponse> getAllCustomers() {
        return customerController.getAllCustomers()
                .stream()
                .map(customer -> genericConverter.toDto(customer, GetCustomerResponse.class))
                .toList();
    }
}
