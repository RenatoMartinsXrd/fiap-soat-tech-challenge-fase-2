package com.hexagonalarch.core.usecases.Payment;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.OrderPaymentGatewayPort;
import com.hexagonalarch.core.ports.gateways.PaymentGatewayPort;
import com.hexagonalarch.core.ports.usecases.Payment.CreatePaymentUseCasePort;
import com.hexagonalarch.core.ports.usecases.Payment.GetPaymentByOrderUseCasePort;

import java.util.List;
import java.util.Optional;

public class GetPaymentByOrderUseCase implements GetPaymentByOrderUseCasePort {

    private final PaymentGatewayPort paymentGatewayPort;
    private final OrderPaymentGatewayPort orderPaymentGatewayPort;

    public GetPaymentByOrderUseCase(PaymentGatewayPort mercadoPagoGateway, OrderPaymentGatewayPort orderPaymentGatewayPort) {
        this.paymentGatewayPort = mercadoPagoGateway;
        this.orderPaymentGatewayPort = orderPaymentGatewayPort;
    }

    @Override
    public OrderPayment getPaymentByOrderId(Long orderId) {
        List<OrderPayment> payments = orderPaymentGatewayPort.findByOrderId(orderId);

        if (payments.isEmpty()) {
            throw new RuntimeException("Nenhum pagamento encontrado para o pedido: " + orderId);
        }
        return payments.get(0);
    }
}