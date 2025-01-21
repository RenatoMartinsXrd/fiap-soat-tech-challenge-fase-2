package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.ports.usecases.Customer.GetCustomerUseCasePort;

import java.util.Optional;

public class GetCustomerUseCase implements GetCustomerUseCasePort {
    private CustomerGatewayPort customerGatewayPort;

    public GetCustomerUseCase(CustomerGatewayPort customerGatewayPort) {
        this.customerGatewayPort = customerGatewayPort;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerGatewayPort.findById(id);
        Customer storedCustomer = customer.orElseThrow(() -> new RuntimeException("Customer not found"));

        return storedCustomer;
    }
}
