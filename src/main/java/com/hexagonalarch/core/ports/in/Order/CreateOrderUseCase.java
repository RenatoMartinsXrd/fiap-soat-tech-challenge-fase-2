package com.hexagonalarch.core.ports.in.Order;

import com.hexagonalarch.core.domain.Order;

public interface CreateOrderUseCase {

    Order createOrder(Order order);

}
