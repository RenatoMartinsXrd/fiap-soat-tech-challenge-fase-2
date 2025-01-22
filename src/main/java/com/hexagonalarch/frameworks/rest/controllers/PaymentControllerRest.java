package com.hexagonalarch.frameworks.rest.controllers;

import com.hexagonalarch.adapters.controllers.OrderController;
import com.hexagonalarch.adapters.controllers.PaymentController;
import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.domain.dto.PaymentNotificationDto;
import com.hexagonalarch.core.domain.enumeration.PaymentStatus;
import com.hexagonalarch.core.ports.usecases.Payment.PaymentWebhookUseCasePort;
import com.hexagonalarch.frameworks.rest.dto.request.MercadoPagoWebhookRequest;
import com.mercadopago.client.payment.PaymentAuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentControllerRest {
    private final PaymentController paymentController;

    public PaymentControllerRest(PaymentController paymentController) {
        this.paymentController = paymentController;
    }

    @PostMapping("/webhook")
    public void handleMercadoPagoWebhook(@RequestBody MercadoPagoWebhookRequest request) {
        String type = request.getType();
        String paymentId = request.getData().getId();
        boolean liveMode = request.isLiveMode();
        String action = request.getAction();
        paymentController.paymentWebhook(new PaymentNotificationDto(type, paymentId, action, liveMode));
    }

    @PostMapping("/qrcode")
    public ResponseEntity<OrderPayment> createPayment(@RequestParam Long orderId) {
        OrderPayment payment = paymentController.createPayment(orderId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderPayment> getPaymentByOrderId(@RequestParam Long orderId) {
        OrderPayment payment = paymentController.getPaymentByOrderId(orderId);
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/order/{orderId}/simulate")
    public ResponseEntity<OrderPayment> getPaymentByOrderId(@RequestParam Long orderId, PaymentStatus paymentStatus) {
        OrderPayment payment = paymentController.updateStatusPayment(orderId, paymentStatus);
        return ResponseEntity.ok(payment);
    }


}
