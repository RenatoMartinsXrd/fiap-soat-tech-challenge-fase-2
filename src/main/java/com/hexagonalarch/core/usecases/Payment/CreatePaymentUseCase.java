package com.hexagonalarch.core.usecases.Payment;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.OrderPaymentGatewayPort;
import com.hexagonalarch.core.ports.gateways.PaymentGatewayPort;
import com.hexagonalarch.core.ports.usecases.Payment.CreatePaymentUseCasePort;
import java.util.Optional;

public class CreatePaymentUseCase implements CreatePaymentUseCasePort {

    private final OrderGatewayPort orderRepository;
    private final PaymentGatewayPort paymentGatewayPort;
    private final OrderPaymentGatewayPort orderPaymentGatewayPort;

    public CreatePaymentUseCase(OrderGatewayPort orderRepository, PaymentGatewayPort mercadoPagoGateway, OrderPaymentGatewayPort orderPaymentGatewayPort) {
        this.orderRepository = orderRepository;
        this.paymentGatewayPort = mercadoPagoGateway;
        this.orderPaymentGatewayPort = orderPaymentGatewayPort;
    }

    @Override
    public OrderPayment createPayment(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new IllegalArgumentException("Pedido não encontrado!");
        }

        Order order = orderOptional.get();
        OrderPayment orderPayment = paymentGatewayPort.generatePayment(order);
        orderPaymentGatewayPort.save(orderPayment);

        return orderPayment;
    }
}