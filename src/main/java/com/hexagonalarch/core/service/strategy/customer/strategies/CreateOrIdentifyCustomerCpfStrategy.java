package com.hexagonalarch.core.service.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.service.strategy.NavigationResult;
import com.hexagonalarch.core.service.strategy.customer.CustomerStrategy;

import java.util.Optional;

public class CreateOrIdentifyCustomerCpfStrategy implements CustomerStrategy {
    private final CustomerRepositoryPort customerRepository;

    public CreateOrIdentifyCustomerCpfStrategy(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public NavigationResult<Customer> execute(Customer c) {
        Optional<Customer> byCpf = customerRepository.findByCpf((c.getCpf()));
        if(byCpf.isPresent()){
            return NavigationResult.success(byCpf.get(), "Cliente identificado");
        }
        Customer save = customerRepository.save(c);
        return NavigationResult.success(save, "Cliente cadastrado pelo nome e email");
    }
}
