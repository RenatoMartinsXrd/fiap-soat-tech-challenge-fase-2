package com.hexagonalarch.frameworks.mercadopago;

import com.hexagonalarch.adapters.presenters.OrderPaymentConverter;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.ports.gateways.OrderPaymentGatewayPort;
import com.hexagonalarch.core.ports.gateways.PaymentGatewayPort;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentMethodRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MercadoPagoGateway implements PaymentGatewayPort {

    private static final String ACCESS_TOKEN = "lldslsdlsdlTEST-1342373300869318-012101-7b050b910f823465e5e9822adef80207-241386899"; // Substitua pelo seu token
    private final OrderPaymentConverter orderPaymentConverter;
    private final OrderPaymentGatewayPort orderPaymentGatewayPort;

    @Override
    public OrderPayment generatePayment(Order order) {
        MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);

        PaymentClient paymentClient = new PaymentClient();
        PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                .notificationUrl("https://20b7-2804-14d-1c7a-980c-fd7d-14ee-7ea7-26a2.ngrok-free.app/payments/webhook")
                .transactionAmount(BigDecimal.valueOf(order.getTotalPrice()))
                .paymentMethodId("pix")
                .paymentMethod(PaymentMethodRequest.builder().type("PIX").build())
                .description("Pedido #" + order.getId())
                .payer(PaymentPayerRequest.builder()
                        .email("anonymous@domain.com")
                        .build())
                .metadata(Map.of(
                        "orderId", order.getId(),
                        "customerName", "Não identificado"
                ))
                .build();

        try {

            Payment payment = paymentClient.create(createRequest);

            return orderPaymentConverter.mercadoPagoPaymentToDomain(payment, order.getId());
        } catch (MPApiException ex) {
            throw new RuntimeException(
                    String.format("Erro da API Mercado Pago: Status: %d, Detalhes: %s",
                            ex.getApiResponse().getStatusCode(),
                            ex.getApiResponse().getContent())
            );
        } catch (MPException ex) {
            throw new RuntimeException("Erro ao integrar com Mercado Pago: " + ex.getMessage(), ex);
        }
    }

    @Override
    public OrderPayment getPaymentByOrderId(OrderPayment orderPayment) {
        MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);
        try {

            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(orderPayment.getMercadoPagoPaymentId());

            return orderPaymentConverter.mercadoPagoPaymentToDomain(payment, orderPayment.getOrderId());

        } catch (MPException ex) {
            throw new RuntimeException("Erro ao buscar pagamento no Mercado Pago: " + ex.getMessage(), ex);
        } catch (MPApiException e) {
            throw new RuntimeException(
                    String.format("Erro da API Mercado Pago: Status: %d, Detalhes: %s",
                            e.getApiResponse().getStatusCode(),
                            e.getApiResponse().getContent())
            );
        }
    }

    @Override
    public OrderPayment updatePaymentStatus(OrderPayment orderPayment) {
        MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);

        try {
            Long paymentId = orderPayment.getMercadoPagoPaymentId();
            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(orderPayment.getMercadoPagoPaymentId());

            paymentClient.capture(paymentId);

            return orderPaymentConverter.mercadoPagoPaymentToDomain(payment, orderPayment.getOrderId());

        } catch (MPException ex) {
            throw new RuntimeException("Erro ao buscar pagamento no Mercado Pago: " + ex.getMessage(), ex);
        } catch (MPApiException e) {
            throw new RuntimeException(
                    String.format("Erro da API Mercado Pago: Status: %d, Detalhes: %s",
                            e.getApiResponse().getStatusCode(),
                            e.getApiResponse().getContent())
            );
        }
    }
}
