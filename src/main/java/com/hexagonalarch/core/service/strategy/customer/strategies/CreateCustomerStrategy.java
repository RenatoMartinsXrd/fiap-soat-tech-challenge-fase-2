package com.hexagonalarch.core.service.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.service.strategy.NavigationResult;
import com.hexagonalarch.core.service.strategy.customer.CustomerStrategy;
import com.hexagonalarch.core.service.validations.factory.CustomerValidationFactory;

public class CreateCustomerStrategy implements CustomerStrategy {
    private final CustomerRepositoryPort customerRepository;

    public CreateCustomerStrategy(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public NavigationResult<Customer> execute(Customer customer) {
        if (!CustomerValidationFactory.getCustomerNotNullValidation().validate(customer).isValid()) {
            Customer save = customerRepository.save(customer);
            return NavigationResult.success(save, "Cliente identificado");
        }
        return NavigationResult.failure("Cliente identificado");
    }
}
