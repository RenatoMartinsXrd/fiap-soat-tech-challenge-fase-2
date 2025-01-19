package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.GetAllOrdersUseCasePort;

import java.util.List;

public class GetAllOrdersUseCase implements GetAllOrdersUseCasePort {
    private OrderGatewayPort orderGatewayPort;

    public GetAllOrdersUseCase(OrderGatewayPort orderGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
    }

    @Override
    public List<Order> getAllOrders(OrderStatus statusFilter) {
        return orderGatewayPort.findAll(statusFilter);
    }
}
