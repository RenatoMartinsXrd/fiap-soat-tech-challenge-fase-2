package com.hexagonalarch.core.service;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.in.Order.CheckoutUseCase;
import com.hexagonalarch.core.ports.out.OrderRepositoryPort;

import java.util.Optional;

public class CheckoutService implements CheckoutUseCase {

    private final OrderRepositoryPort orderRepository;

    public CheckoutService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order processCheckout(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.get();
        order.setStatus(OrderStatus.FINALIZADO);
        return orderRepository.save(order);
    }
}
