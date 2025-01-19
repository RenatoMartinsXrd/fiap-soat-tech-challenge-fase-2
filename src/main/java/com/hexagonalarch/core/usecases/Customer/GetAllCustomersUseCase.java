package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.ports.usecases.Customer.GetAllCustomersUseCasePort;

import java.util.List;

public class GetAllCustomersUseCase implements GetAllCustomersUseCasePort {
    private CustomerGatewayPort customerGatewayPort;

    public GetAllCustomersUseCase(CustomerGatewayPort customerGatewayPort) {
        this.customerGatewayPort = customerGatewayPort;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerGatewayPort.findAll();
    }
}
