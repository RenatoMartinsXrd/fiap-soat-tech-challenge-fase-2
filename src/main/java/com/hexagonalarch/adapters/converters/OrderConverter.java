package com.hexagonalarch.adapters.converters;

import com.hexagonalarch.adapters.dto.request.CreateOrderRequest;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    public Order toDomain(CreateOrderRequest request) {
        List<Product> products = request.getProductIds().stream()
                .map(id -> new Product(id))
                .collect(Collectors.toList());
        return new Order(null, request.getCustomerId(), null, products);
    }

}
