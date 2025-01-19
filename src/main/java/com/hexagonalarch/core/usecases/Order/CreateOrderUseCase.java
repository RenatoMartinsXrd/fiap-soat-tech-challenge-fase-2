package com.hexagonalarch.core.usecases.Order;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Order.CreateOrderUseCasePort;
import com.hexagonalarch.core.usecases.validations.factory.OrderValidationFactory;
import com.hexagonalarch.shared.exception.NotFoundException;

import java.util.List;

public class CreateOrderUseCase implements CreateOrderUseCasePort {
    private final OrderGatewayPort orderGatewayPort;
    private final CustomerGatewayPort customerGatewayPort;
    private final ProductGatewayPort productGatewayPort;

    public CreateOrderUseCase(OrderGatewayPort orderGatewayPort, CustomerGatewayPort customerGatewayPort, ProductGatewayPort productGatewayPort) {
        this.orderGatewayPort = orderGatewayPort;
        this.customerGatewayPort = customerGatewayPort;
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Order createOrder(Order order) {
        Customer customer = customerGatewayPort.findById(order.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        order.setCustomerId(customer.getId());

        List<Product> products = order.getProducts().stream()
                .map(product -> productGatewayPort.findById(product.getId())
                        .orElseThrow(() -> new NotFoundException("Product not found for ID: " + product.getId())))
                .toList();
        order.setProducts(products);

        order.setStatus(OrderStatus.INICIADO);

        OrderValidationFactory.getValidatorsForCreateOrder().validate(order);

        return orderGatewayPort.save(order);
    }
}
