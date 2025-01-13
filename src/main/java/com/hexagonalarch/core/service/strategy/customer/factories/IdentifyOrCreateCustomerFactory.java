package com.hexagonalarch.core.service.strategy.customer.factories;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.service.strategy.customer.*;
import com.hexagonalarch.core.service.strategy.customer.strategies.CreateOrIdentifyCustomerCpfStrategy;
import com.hexagonalarch.core.service.strategy.customer.strategies.CreateOrIdentifyCustomerWithoutCpfStrategy;
import com.hexagonalarch.core.service.strategy.customer.strategies.DefaultCustomerStrategy;
import com.hexagonalarch.core.service.validations.ValidationResult;
import com.hexagonalarch.core.service.validations.factory.CustomerValidationFactory;
import com.hexagonalarch.exception.BusinessException;

public class IdentifyOrCreateCustomerFactory {
    private final CustomerRepositoryPort customerRepository;

    public IdentifyOrCreateCustomerFactory(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerStrategy getStrategy(Customer customer) {
        ValidationResult customerNotNull = CustomerValidationFactory.getCustomerNotNullValidation().validate(customer);
        boolean isValidEmail = CustomerValidationFactory.getEmailValidation().validate(customer).isValid();
        boolean isValidName = CustomerValidationFactory.getNameValidation().validate(customer).isValid();
        boolean isValidCpf = CustomerValidationFactory.getCustomerCpfValidation().validate(customer).isValid();

        if(!customerNotNull.isValid()){
            throw new BusinessException(customerNotNull.getMessage());
        }
        if (!isValidCpf && isValidEmail && isValidName) {
            return new CreateOrIdentifyCustomerWithoutCpfStrategy(customerRepository);
        }

        if(isValidCpf){
            return new CreateOrIdentifyCustomerCpfStrategy(customerRepository);
        }

       return new DefaultCustomerStrategy();
    }
}