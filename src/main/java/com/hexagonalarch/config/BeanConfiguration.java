package com.hexagonalarch.config;

import com.hexagonalarch.adapters.inbound.ServicesFacade.CustomerServiceFacade;
import com.hexagonalarch.adapters.inbound.ServicesFacade.OrderServiceFacade;
import com.hexagonalarch.adapters.inbound.ServicesFacade.ProductServiceFacade;
import com.hexagonalarch.core.ports.in.Order.CheckoutUseCase;
import com.hexagonalarch.core.ports.in.Customer.CreateCustomerUseCase;
import com.hexagonalarch.core.ports.in.Customer.GetAllCustomersUseCase;
import com.hexagonalarch.core.ports.in.Customer.GetCustomerUseCase;
import com.hexagonalarch.core.ports.in.Customer.IdentifyOrCreateCustomerUseCase;
import com.hexagonalarch.core.ports.in.Order.CreateOrderUseCase;
import com.hexagonalarch.core.ports.in.Order.GetAllOrdersUseCase;
import com.hexagonalarch.core.ports.in.Order.GetOrderUseCase;
import com.hexagonalarch.core.ports.in.Order.UpdateOrderStatusUseCase;
import com.hexagonalarch.core.ports.in.Product.CreateProductUseCase;
import com.hexagonalarch.core.ports.in.Product.GetAllProductsUseCase;
import com.hexagonalarch.core.ports.in.Product.GetProductUseCase;
import com.hexagonalarch.core.ports.out.CustomerRepositoryPort;
import com.hexagonalarch.core.ports.out.OrderRepositoryPort;
import com.hexagonalarch.core.ports.out.ProductRepositoryPort;
import com.hexagonalarch.core.service.CheckoutService;
import com.hexagonalarch.core.service.CustomerService;
import com.hexagonalarch.core.service.OrderService;
import com.hexagonalarch.core.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomerService customerService(CustomerRepositoryPort customerRepositoryPort) {
        return new CustomerService(customerRepositoryPort);
    }

    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }

    @Bean
    public CheckoutService checkoutService(OrderRepositoryPort orderRepositoryPort) {
        return new CheckoutService(orderRepositoryPort);
    }


    @Bean
    public OrderService orderService(OrderRepositoryPort orderRepositoryPort, CustomerRepositoryPort customerRepository, ProductRepositoryPort productRepository) {
        return new OrderService(orderRepositoryPort, customerRepository, productRepository);
    }

    @Bean
    public CustomerServiceFacade customerServiceFacade(CreateCustomerUseCase customerService, GetCustomerUseCase getCustomerUseCase, GetAllCustomersUseCase getAllCustomersUseCase, IdentifyOrCreateCustomerUseCase identifyOrCreateCustomerUseCase) {
        return new CustomerServiceFacade(customerService, getCustomerUseCase, getAllCustomersUseCase, identifyOrCreateCustomerUseCase);
    }

    @Bean
    public ProductServiceFacade productServiceFacade(CreateProductUseCase createProductUseCase, GetProductUseCase getProductUseCase, GetAllProductsUseCase getAllProductsUseCase) {
        return new ProductServiceFacade(createProductUseCase, getProductUseCase, getAllProductsUseCase);
    }

    @Bean
    public OrderServiceFacade orderServiceFacade(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, GetAllOrdersUseCase getAllOrdersUseCase, UpdateOrderStatusUseCase updateOrderStatusUseCase, CheckoutUseCase checkoutUseCase, CustomerRepositoryPort customerRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        return new OrderServiceFacade(createOrderUseCase, getOrderUseCase, getAllOrdersUseCase, updateOrderStatusUseCase, checkoutUseCase, customerRepositoryPort, productRepositoryPort);
    }
}
