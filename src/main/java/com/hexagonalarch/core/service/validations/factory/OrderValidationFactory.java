package com.hexagonalarch.core.service.validations.factory;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.service.validations.CompositeValidator;
import com.hexagonalarch.core.service.validations.Validator;
import com.hexagonalarch.core.service.validations.order.MinimalProductQuantityValidation;

import java.util.List;

public class OrderValidationFactory {

    public static Validator<Order> getValidatorsForCreateOrder() {
        return new CompositeValidator<>(List.of(
                new MinimalProductQuantityValidation()
        ));
    }
}
