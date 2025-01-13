package com.hexagonalarch.core.service.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.service.validations.ValidationResult;
import com.hexagonalarch.core.service.validations.Validator;

public class CustomerEmailValidation implements Validator<Customer> {
    @Override
    public ValidationResult validate(Customer customer) {
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            return new ValidationResult(false, "Email não enviado");
        }
        return new ValidationResult(true, null);
    }
}
