package com.hexagonalarch.core.ports.in.Order;

import com.hexagonalarch.core.domain.Order;

public interface CheckoutUseCase {
    Order processCheckout(Long id);
}
