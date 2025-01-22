package com.hexagonalarch.frameworks.jpa;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.domain.OrderPayment;
import com.hexagonalarch.core.ports.gateways.OrderPaymentGatewayPort;
import com.hexagonalarch.frameworks.jpa.entity.OrderPaymentEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaOrderPaymentRepositoryAdapter implements OrderPaymentGatewayPort {
    private final JpaOrderPaymentRepository jpaOrderPaymentRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderPayment save(OrderPayment orderPayment) {
        OrderPaymentEntity payment = jpaOrderPaymentRepository.save(modelMapper.map(orderPayment, OrderPaymentEntity.class));
        return modelMapper.map(payment, OrderPayment.class);
    }

    @Override
    public OrderPayment findByMercadoPagoPaymentId(Long id) {
        OrderPaymentEntity byMercadoPagoPaymentId = jpaOrderPaymentRepository.findByMercadoPagoPaymentId(id);
        return modelMapper.map(byMercadoPagoPaymentId, OrderPayment.class);
    }

    @Override
    public List<OrderPayment> findByOrderId(Long orderId) {
        return jpaOrderPaymentRepository.findByOrderIdOrderByCreatedAtDesc(orderId).stream()
                .map(entity -> modelMapper.map(entity, OrderPayment.class))
                .collect(Collectors.toList());
    }
}
