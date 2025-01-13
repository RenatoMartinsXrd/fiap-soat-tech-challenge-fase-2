package com.hexagonalarch.core.service.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.service.strategy.NavigationResult;
import com.hexagonalarch.core.service.strategy.customer.CustomerStrategy;

import java.util.Optional;

public class CreateOrIdentifyCustomerWithoutCpfStrategy implements CustomerStrategy {
    private final CustomerRepositoryPort customerRepository;

    public CreateOrIdentifyCustomerWithoutCpfStrategy(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public NavigationResult<Customer> execute(Customer c) {
        Optional<Customer> byEmail = customerRepository.findByEmail((c.getEmail()));
        if(byEmail.isPresent()){
            return NavigationResult.success(byEmail.get(), "Cliente identificado");
        }
        Customer save = customerRepository.save(c);
        return NavigationResult.success(save, "Cliente identificado");
    }
}
