package com.hexagonalarch.core.ports.in.Customer;

import com.hexagonalarch.core.domain.Customer;

public interface IdentifyOrCreateCustomerUseCase {
    Customer identifyOrCreateCustomer(Customer c);
}
