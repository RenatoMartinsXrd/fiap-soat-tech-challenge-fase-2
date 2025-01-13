package com.hexagonalarch.core.ports.in.Order;

import com.hexagonalarch.core.domain.Order;

public interface GetOrderUseCase {

    Order getOrderById(Long id);

}
