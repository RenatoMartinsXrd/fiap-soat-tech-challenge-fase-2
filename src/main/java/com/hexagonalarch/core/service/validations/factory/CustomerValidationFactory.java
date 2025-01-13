package com.hexagonalarch.core.service.validations.factory;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.service.validations.CompositeValidator;
import com.hexagonalarch.core.service.validations.Validator;
import com.hexagonalarch.core.service.validations.customer.CustomerCpfValidation;
import com.hexagonalarch.core.service.validations.customer.CustomerEmailValidation;
import com.hexagonalarch.core.service.validations.customer.CustomerIsNotNullValidation;
import com.hexagonalarch.core.service.validations.customer.CustomerNameValidation;

import java.util.List;

public class CustomerValidationFactory {

    public static Validator<Customer> getCustomerNotNullValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerIsNotNullValidation()
        ));
    }

    public static Validator<Customer> getNameValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerNameValidation()
        ));
    }

    public static Validator<Customer> getEmailValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerEmailValidation()
        ));
    }

    public static Validator<Customer> getCustomerCpfValidation() {
        return new CompositeValidator<>(List.of(
                new CustomerCpfValidation()
        ));
    }
}
