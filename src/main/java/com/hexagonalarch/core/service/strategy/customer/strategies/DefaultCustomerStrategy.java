package com.hexagonalarch.core.service.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.service.strategy.NavigationResult;
import com.hexagonalarch.core.service.strategy.customer.CustomerStrategy;

public class DefaultCustomerStrategy implements CustomerStrategy {
    @Override
    public NavigationResult execute(Customer customer) {
        return NavigationResult.failure("Nenhuma estratégia aplicável para o cliente.");
    }
}