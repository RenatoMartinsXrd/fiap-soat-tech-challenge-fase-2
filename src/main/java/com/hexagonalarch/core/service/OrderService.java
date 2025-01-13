package com.hexagonalarch.core.service;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.in.Order.CreateOrderUseCase;
import com.hexagonalarch.core.ports.in.Order.GetAllOrdersUseCase;
import com.hexagonalarch.core.ports.in.Order.GetOrderUseCase;
import com.hexagonalarch.core.ports.in.Order.UpdateOrderStatusUseCase;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.ports.out.OrderRepositoryPort;
import com.hexagonalarch.core.ports.out.ProductRepositoryPort;
import com.hexagonalarch.core.service.validations.factory.OrderValidationFactory;
import com.hexagonalarch.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public class OrderService implements CreateOrderUseCase, GetOrderUseCase, GetAllOrdersUseCase, UpdateOrderStatusUseCase {

    private final OrderRepositoryPort orderRepository;
    private final CustomerRepositoryPort customerRepository;
    private final ProductRepositoryPort productRepository;

    public OrderService(OrderRepositoryPort orderRepository, CustomerRepositoryPort customerRepository, ProductRepositoryPort productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        order.setCustomerId(customer.getId());

        List<Product> products = order.getProducts().stream()
                .map(product -> productRepository.findById(product.getId())
                        .orElseThrow(() -> new NotFoundException("Product not found for ID: " + product.getId())))
                .toList();
        order.setProducts(products);

        order.setStatus(OrderStatus.INICIADO);

        OrderValidationFactory.getValidatorsForCreateOrder().validate(order);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders(OrderStatus statusFilter) {
        return orderRepository.findAll(statusFilter);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        //Revisar Update
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }
}
