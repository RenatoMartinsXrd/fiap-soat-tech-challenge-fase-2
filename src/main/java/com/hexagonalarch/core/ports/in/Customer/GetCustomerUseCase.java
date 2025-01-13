package com.hexagonalarch.core.ports.in.Customer;

import com.hexagonalarch.core.domain.Customer;

public interface GetCustomerUseCase {

    Customer getCustomerById(Long id);

}
