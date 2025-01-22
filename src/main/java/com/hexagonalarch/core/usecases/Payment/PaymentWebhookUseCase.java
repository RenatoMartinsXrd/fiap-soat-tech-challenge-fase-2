package com.hexagonalarch.core.usecases.Payment;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.domain.dto.PaymentNotificationDto;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.domain.enumeration.PaymentStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.OrderPaymentGatewayPort;
import com.hexagonalarch.core.ports.usecases.Payment.PaymentWebhookUseCasePort;

import java.util.List;
import java.util.Optional;

public class PaymentWebhookUseCase implements PaymentWebhookUseCasePort {
    private OrderPaymentGatewayPort paymentGatewayPort;
    private OrderGatewayPort orderGatewayPort;

    public PaymentWebhookUseCase(OrderPaymentGatewayPort paymentGatewayPort, OrderGatewayPort orderGatewayPort) {
        this.paymentGatewayPort = paymentGatewayPort;
        this.orderGatewayPort = orderGatewayPort;
    }

    @Override
    public void processPaymentWebhook(PaymentNotificationDto paymentNotificationDto) {
        Long aLong = Long.parseLong(paymentNotificationDto.getPaymentId());

        List<OrderPayment> byOrderId = paymentGatewayPort.findByOrderId(aLong);
        OrderPayment orderPayment = byOrderId.get(0);
        if (orderPayment == null) {
            throw new RuntimeException("Pagamento não encontrado para o MercadoPagoPaymentId: " + aLong);
        }

        if(orderPayment.getStatus() == PaymentStatus.APPROVED){
            Optional<Order> byId = orderGatewayPort.findById(orderPayment.getOrderId());

            Order order = byId.get();
            order.setStatus(OrderStatus.RECEBIDO);
            orderGatewayPort.save(order);
        }
        System.out.println(paymentNotificationDto);

    }
}
