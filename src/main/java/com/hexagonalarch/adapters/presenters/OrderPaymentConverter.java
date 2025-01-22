package com.hexagonalarch.adapters.presenters;

import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.domain.enumeration.PaymentStatus;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderPaymentConverter {
    public OrderPayment mercadoPagoPaymentToDomain(Payment payment, Long orderId) {
        if (payment == null) return null;

        PaymentStatus paymentStatus = mapMercadoPagoStatusToPaymentStatus(payment.getStatus());
        return new OrderPayment(payment.getId(), paymentStatus, orderId, LocalDateTime.now(), LocalDateTime.now());
    }

    private PaymentStatus mapMercadoPagoStatusToPaymentStatus(String mercadoPagoStatus) {
        try {
            return PaymentStatus.valueOf(mercadoPagoStatus.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return PaymentStatus.PENDING;
        }
    }
}
