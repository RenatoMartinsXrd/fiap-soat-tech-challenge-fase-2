package com.hexagonalarch.core.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.ports.usecases.Customer.IdentifyOrCreateCustomerUseCasePort;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import com.hexagonalarch.core.usecases.strategy.customer.CustomerStrategy;
import com.hexagonalarch.core.usecases.strategy.customer.factories.IdentifyOrCreateCustomerFactory;
import com.hexagonalarch.shared.exception.BusinessException;

public class IdentifyOrCreateCustomerUseCase implements IdentifyOrCreateCustomerUseCasePort {
    private CustomerGatewayPort customerGateway;

    public IdentifyOrCreateCustomerUseCase(CustomerGatewayPort customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public Customer identifyOrCreateCustomer(Customer c) {
        IdentifyOrCreateCustomerFactory factory = new IdentifyOrCreateCustomerFactory(customerGateway);
        CustomerStrategy strategy = factory.getStrategy(c);

        NavigationResult<Customer> result = strategy.execute(c);
        if (!result.getValidationResult().isValid()) {
            throw new BusinessException(result.getValidationResult().getMessage());
        }

        return result.getResult();
    }
}
