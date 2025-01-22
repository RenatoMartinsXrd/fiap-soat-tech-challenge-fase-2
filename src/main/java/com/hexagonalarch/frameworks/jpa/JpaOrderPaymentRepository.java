package com.hexagonalarch.frameworks.jpa;

import com.hexagonalarch.frameworks.jpa.entity.OrderEntity;
import com.hexagonalarch.frameworks.jpa.entity.OrderPaymentEntity;
import com.hexagonalarch.frameworks.jpa.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaOrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Long> {
    OrderPaymentEntity findByMercadoPagoPaymentId(Long mercadoPagoPaymentId);
    List<OrderPaymentEntity> findByOrderIdOrderByCreatedAtDesc(Long orderId);
}
