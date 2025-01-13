package com.hexagonalarch.adapters.inbound.ServicesFacade;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.enumeration.OrderStatus;
import com.hexagonalarch.core.ports.in.Order.CheckoutUseCase;
import com.hexagonalarch.core.ports.in.Order.CreateOrderUseCase;
import com.hexagonalarch.core.ports.in.Order.GetAllOrdersUseCase;
import com.hexagonalarch.core.ports.in.Order.GetOrderUseCase;
import com.hexagonalarch.core.ports.in.Order.UpdateOrderStatusUseCase;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.ports.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceFacade {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final CheckoutUseCase checkoutUseCase;
    private final CustomerRepositoryPort customerRepository;
    private final ProductRepositoryPort productRepository;

    public OrderServiceFacade(CreateOrderUseCase createOrderUseCase,
                              GetOrderUseCase getOrderUseCase,
                              GetAllOrdersUseCase getAllOrdersUseCase,
                              UpdateOrderStatusUseCase updateOrderStatusUseCase, CheckoutUseCase checkoutUseCase,
                              CustomerRepositoryPort customerRepository,
                              ProductRepositoryPort productRepository) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.checkoutUseCase = checkoutUseCase;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    public Order createOrder(Order order) {
        return createOrderUseCase.createOrder(order);
    }

    public Order getOrderById(Long id) {
        return getOrderUseCase.getOrderById(id);
    }

    public List<Order> getAllOrders(OrderStatus statusFilter) {
        return getAllOrdersUseCase.getAllOrders(statusFilter);
    }

    public void updateOrderStatus(Long id, OrderStatus status) {
        updateOrderStatusUseCase.updateOrderStatus(id, status);
    }

    public Order checkout(Long id){
        return checkoutUseCase.processCheckout(id);
    }
}
