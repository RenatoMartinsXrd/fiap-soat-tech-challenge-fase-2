package com.hexagonalarch.adapters.inbound;

import com.hexagonalarch.adapters.converters.GenericConverter;
import com.hexagonalarch.adapters.converters.OrderConverter;
import com.hexagonalarch.adapters.dto.request.CreateOrderRequest;
import com.hexagonalarch.adapters.dto.request.UpdateOrderStatusRequest;
import com.hexagonalarch.adapters.dto.response.CreateOrderResponse;
import com.hexagonalarch.adapters.dto.response.GetOrderResponse;
import com.hexagonalarch.adapters.inbound.ServicesFacade.OrderServiceFacade;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Operations related to Orders")
public class OrderController {

    private final OrderServiceFacade orderServiceFacade;
    private final GenericConverter genericConverter;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderConverter.toDomain(createOrderRequest);
        Order newOrder = orderServiceFacade.createOrder(order);
        return genericConverter.toDto(newOrder, CreateOrderResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetOrderResponse getOrderById(@PathVariable Long id) {
        Order order = orderServiceFacade.getOrderById(id);
        return genericConverter.toDto(order, GetOrderResponse.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetOrderResponse> getAllOrders(@RequestParam(required = false) OrderStatus statusFilter) {
        List<Order> orders = orderServiceFacade.getAllOrders(statusFilter);
        return orders.stream()
                .map(order -> genericConverter.toDto(order, GetOrderResponse.class))
                .toList();
    }

    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        orderServiceFacade.updateOrderStatus(id, updateOrderStatusRequest.getStatus());
    }

    @PatchMapping("/{id}/checkout")
    @ResponseStatus(HttpStatus.OK)
    public GetOrderResponse checkout(@PathVariable Long id) {
        Order checkout = orderServiceFacade.checkout(id);
        return genericConverter.toDto(checkout, GetOrderResponse.class);
    }
}
