package com.hexagonalarch.core.ports.in.Order;

import com.hexagonalarch.core.domain.enumeration.OrderStatus;

public interface UpdateOrderStatusUseCase {

    void updateOrderStatus(Long id, OrderStatus status);

}
