package com.hexagonalarch.core.usecases.Payment;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.domain.dto.PaymentNotificationDto;
import com.hexagonalarch.core.domain.enumeration.PaymentStatus;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.OrderPaymentGatewayPort;
import com.hexagonalarch.core.ports.gateways.PaymentGatewayPort;
import com.hexagonalarch.core.ports.usecases.Payment.CreatePaymentUseCasePort;
import com.hexagonalarch.core.ports.usecases.Payment.UpdatePaymentStatusUseCasePort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UpdatePaymentStatusUseCase implements UpdatePaymentStatusUseCasePort {
    private final OrderPaymentGatewayPort orderPaymentGatewayPort;
    private final PaymentWebhookUseCase paymentWebhookUseCase;

    public UpdatePaymentStatusUseCase(OrderPaymentGatewayPort orderPaymentGatewayPort, PaymentWebhookUseCase paymentWebhookUseCase) {
        this.orderPaymentGatewayPort = orderPaymentGatewayPort;
        this.paymentWebhookUseCase = paymentWebhookUseCase;
    }

    @Override
    public OrderPayment updateStatusPayment(Long orderId, PaymentStatus paymentStatus) {
        List<OrderPayment> byOrderId = orderPaymentGatewayPort.findByOrderId(orderId);
        if(byOrderId.isEmpty()) new RuntimeException("Pagamento não encontrado para a orderId: " + orderId);

        OrderPayment updatedOrderPayment = byOrderId.get(0);
        updatedOrderPayment.setStatus(paymentStatus);
        updatedOrderPayment.setCreatedAt(LocalDateTime.now());


        orderPaymentGatewayPort.save(updatedOrderPayment);
        paymentWebhookUseCase.processPaymentWebhook(new PaymentNotificationDto("event", updatedOrderPayment.getOrderId().toString(), "sd", true));

        return updatedOrderPayment;
    }
}