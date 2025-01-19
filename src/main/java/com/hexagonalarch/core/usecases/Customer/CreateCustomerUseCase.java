package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.ports.usecases.Customer.CreateCustomerUseCasePort;

public class CreateCustomerUseCase implements CreateCustomerUseCasePort {
    private CustomerGatewayPort customerGateway;

    public CreateCustomerUseCase(CustomerGatewayPort customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public Customer createCustomer(Customer customer) {
            return customerGateway.save(customer);
    }
}
