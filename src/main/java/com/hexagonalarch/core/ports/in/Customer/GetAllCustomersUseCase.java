package com.hexagonalarch.core.ports.in.Customer;

import com.hexagonalarch.core.domain.Customer;

import java.util.List;

public interface GetAllCustomersUseCase {

    List<Customer> getAllCustomers();

}
