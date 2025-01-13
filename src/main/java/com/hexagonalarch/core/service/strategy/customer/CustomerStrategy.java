package com.hexagonalarch.core.service.strategy.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.service.strategy.NavigationResult;
import com.hexagonalarch.core.service.validations.ValidationResult;

public interface CustomerStrategy {
    NavigationResult<Customer>  execute(Customer customer);
}
