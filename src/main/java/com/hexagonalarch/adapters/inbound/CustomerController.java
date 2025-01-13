package com.hexagonalarch.adapters.inbound;

import com.hexagonalarch.adapters.converters.GenericConverter;
import com.hexagonalarch.adapters.dto.request.CreateCustomerRequest;
import com.hexagonalarch.adapters.dto.response.CreateCustomerResponse;
import com.hexagonalarch.adapters.dto.response.GetCustomerResponse;
import com.hexagonalarch.adapters.inbound.ServicesFacade.CustomerServiceFacade;
import com.hexagonalarch.core.domain.Customer;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Operations related to Customers")
public class CustomerController {

    private final CustomerServiceFacade customerServiceFacade;
    private final GenericConverter genericConverter;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest customerRequest) {
        Customer customerInput = genericConverter.toDomain(customerRequest, Customer.class);
        Customer newCustomer = customerServiceFacade.createCustomer(customerInput);
        return genericConverter.toDto(newCustomer, CreateCustomerResponse.class);
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerResponse getCustomerById(@PathVariable Long id) {
        Customer customer = customerServiceFacade.getCustomerById(id);
        return genericConverter.toDto(customer, GetCustomerResponse.class);
    }

    @PostMapping("/identifyOrCreate")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerResponse identifyOrCreate(@Valid @RequestBody CreateCustomerRequest customerRequest) {
        Customer customerInput = genericConverter.toDomain(customerRequest, Customer.class);
        Customer customer = customerServiceFacade.identifyOrCreateCustomer(customerInput);
        return genericConverter.toDto(customer, GetCustomerResponse.class);
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<GetCustomerResponse> getAllCustomers() {
        return customerServiceFacade.getAllCustomers()
                .stream()
                .map(customer -> genericConverter.toDto(customer, GetCustomerResponse.class))
                .toList();
    }
}
