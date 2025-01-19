package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.GetOrderUseCasePort;
import com.hexagonalarch.shared.exception.NotFoundException;

import java.util.Optional;

public class GetOrderUseCase implements GetOrderUseCasePort {
    private final OrderGatewayPort orderGatewayPort;

    public GetOrderUseCase(OrderGatewayPort orderGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderGatewayPort.findById(id);
        return order.orElseThrow(() -> new NotFoundException("Order not found"));
    }
}
