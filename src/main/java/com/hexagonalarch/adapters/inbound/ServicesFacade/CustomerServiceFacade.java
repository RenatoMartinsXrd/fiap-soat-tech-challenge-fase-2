package com.hexagonalarch.adapters.inbound.ServicesFacade;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.in.Customer.CreateCustomerUseCase;
import com.hexagonalarch.core.ports.in.Customer.GetAllCustomersUseCase;
import com.hexagonalarch.core.ports.in.Customer.GetCustomerUseCase;
import com.hexagonalarch.core.ports.in.Customer.IdentifyOrCreateCustomerUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceFacade {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final IdentifyOrCreateCustomerUseCase identifyOrCreateCustomerUseCase;

    public CustomerServiceFacade(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase, GetAllCustomersUseCase getAllCustomersUseCase, IdentifyOrCreateCustomerUseCase identifyOrCreateCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.identifyOrCreateCustomerUseCase = identifyOrCreateCustomerUseCase;
    }

    public Customer createCustomer(Customer customer) {
        return createCustomerUseCase.createCustomer(customer);
    }

    public Customer getCustomerById(Long id) {
        return getCustomerUseCase.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return getAllCustomersUseCase.getAllCustomers();
    }

    public Customer identifyOrCreateCustomer(Customer customer){
        return identifyOrCreateCustomerUseCase.identifyOrCreateCustomer(customer);
    }
}