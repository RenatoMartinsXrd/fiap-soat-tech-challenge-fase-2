package com.hexagonalarch.shared.config;

import com.hexagonalarch.adapters.controllers.CustomerController;
import com.hexagonalarch.adapters.controllers.OrderController;
import com.hexagonalarch.adapters.controllers.ProductController;
import com.hexagonalarch.adapters.presenters.GenericConverter;
import com.hexagonalarch.core.ports.usecases.Order.CheckoutUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.CreateCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetAllCustomersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.IdentifyOrCreateCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.CreateOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetAllOrdersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.UpdateOrderStatusUseCasePort;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.ports.gateways.OrderGatewayPort;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Product.CreateProductUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetAllProductsUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetProductUseCasePort;
import com.hexagonalarch.core.usecases.Customer.CreateCustomerUseCase;
import com.hexagonalarch.core.usecases.Customer.GetAllCustomersUseCase;
import com.hexagonalarch.core.usecases.Customer.GetCustomerUseCase;
import com.hexagonalarch.core.usecases.Customer.IdentifyOrCreateCustomerUseCase;
import com.hexagonalarch.core.usecases.Order.*;
import com.hexagonalarch.core.usecases.Product.CreateProductUseCase;
import com.hexagonalarch.core.usecases.Product.GetAllProductsUseCase;
import com.hexagonalarch.core.usecases.Product.GetProductUseCase;
import com.hexagonalarch.frameworks.rest.controllers.CustomerControllerRest;
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
    public CustomerController customerController(
            CreateCustomerUseCasePort createCustomerUseCase,
            GetCustomerUseCasePort getCustomerUseCase,
            GetAllCustomersUseCasePort getAllCustomersUseCase,
            IdentifyOrCreateCustomerUseCasePort identifyOrCreateCustomerUseCase) {
        return new CustomerController(
                createCustomerUseCase,
                getCustomerUseCase,
                getAllCustomersUseCase,
                identifyOrCreateCustomerUseCase);
    }

    @Bean
    public ProductController productController(
            CreateProductUseCasePort createProductUseCasePort,
            GetProductUseCasePort getProductUseCasePort,
            GetAllProductsUseCasePort getAllProductsUseCasePort) {
        return new ProductController(createProductUseCasePort, getProductUseCasePort, getAllProductsUseCasePort);
    }

    @Bean
    public OrderController orderController(CreateOrderUseCasePort createOrderUseCase,
                                           GetOrderUseCasePort getOrderUseCase,
                                           GetAllOrdersUseCasePort getAllOrdersUseCase,
                                           UpdateOrderStatusUseCasePort updateOrderStatusUseCase,
                                           CheckoutUseCasePort checkoutUseCase) {
        return new OrderController(createOrderUseCase, getOrderUseCase, getAllOrdersUseCase, updateOrderStatusUseCase, checkoutUseCase);
    }

    @Bean
    public CreateOrderUseCasePort createOrderUseCasePort(OrderGatewayPort orderGatewayPort, CustomerGatewayPort customerGatewayPort, ProductGatewayPort productGatewayPort) {
        return new CreateOrderUseCase(orderGatewayPort, customerGatewayPort, productGatewayPort);
    }

    @Bean
    public GetOrderUseCasePort GetOrderUseCasePort(OrderGatewayPort orderGatewayPort) {
        return new GetOrderUseCase(orderGatewayPort);
    }

    @Bean
    public GetAllOrdersUseCasePort GetAllOrdersUseCasePort(OrderGatewayPort orderGatewayPort) {
        return new GetAllOrdersUseCase(orderGatewayPort);
    }

    @Bean
    public CheckoutUseCasePort checkoutUseCasePort(OrderGatewayPort orderRepositoryPort) {
        return new CheckoutOrderUseCase(orderRepositoryPort);
    }

    @Bean
    public UpdateOrderStatusUseCasePort updateOrderStatusUseCasePort(OrderGatewayPort orderRepositoryPort) {
        return new UpdateOrderStatusUseCase(orderRepositoryPort);
    }

    @Bean
    public CreateProductUseCasePort createProductUseCasePort(ProductGatewayPort productGatewayPort) {
        return new CreateProductUseCase(productGatewayPort);
    }

    @Bean
    public GetProductUseCasePort getProductUseCasePort(ProductGatewayPort productGatewayPort) {
        return new GetProductUseCase(productGatewayPort);
    }

    @Bean
    public GetAllProductsUseCasePort getAllProductsUseCasePort(ProductGatewayPort productGatewayPort) {
        return new GetAllProductsUseCase(productGatewayPort);
    }

    @Bean
    public CreateCustomerUseCasePort createCustomerUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new CreateCustomerUseCase(customerGatewayPort);
    }

    @Bean
    public GetCustomerUseCasePort getCustomerUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new GetCustomerUseCase(customerGatewayPort);
    }

    @Bean
    public GetAllCustomersUseCasePort getAllCustomersUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new GetAllCustomersUseCase(customerGatewayPort);
    }

    @Bean
    public IdentifyOrCreateCustomerUseCasePort identifyOrCreateCustomerUseCasePort(CustomerGatewayPort customerGatewayPort) {
        return new IdentifyOrCreateCustomerUseCase(customerGatewayPort);
    }
}
