package com.hexagonalarch.shared.config;

import com.hexagonalarch.adapters.controllers.CustomerController;
import com.hexagonalarch.adapters.controllers.OrderController;
import com.hexagonalarch.adapters.controllers.PaymentController;
import com.hexagonalarch.adapters.controllers.ProductController;
import com.hexagonalarch.adapters.presenters.GenericConverter;
import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.ports.gateways.*;
import com.hexagonalarch.core.ports.usecases.Order.CheckoutUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.CreateCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetAllCustomersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.GetCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Customer.IdentifyOrCreateCustomerUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.CreateOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetAllOrdersUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.GetOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Order.UpdateOrderStatusUseCasePort;
import com.hexagonalarch.core.ports.usecases.Payment.CreatePaymentUseCasePort;
import com.hexagonalarch.core.ports.usecases.Payment.GetPaymentByOrderUseCasePort;
import com.hexagonalarch.core.ports.usecases.Payment.PaymentWebhookUseCasePort;
import com.hexagonalarch.core.ports.usecases.Payment.UpdatePaymentStatusUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.CreateProductUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetAllProductsUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetProductUseCasePort;
import com.hexagonalarch.core.usecases.Customer.CreateCustomerUseCase;
import com.hexagonalarch.core.usecases.Customer.GetAllCustomersUseCase;
import com.hexagonalarch.core.usecases.Customer.GetCustomerUseCase;
import com.hexagonalarch.core.usecases.Customer.IdentifyOrCreateCustomerUseCase;
import com.hexagonalarch.core.usecases.Order.*;
import com.hexagonalarch.core.usecases.Payment.CreatePaymentUseCase;
import com.hexagonalarch.core.usecases.Payment.GetPaymentByOrderUseCase;
import com.hexagonalarch.core.usecases.Payment.PaymentWebhookUseCase;
import com.hexagonalarch.core.usecases.Payment.UpdatePaymentStatusUseCase;
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
    public PaymentController paymentController(PaymentWebhookUseCasePort paymentWebhookUseCasePort,
                                               CreatePaymentUseCasePort createPaymentUseCasePort,
                                               GetPaymentByOrderUseCasePort getPaymentByOrderUseCasePort,
                                               UpdatePaymentStatusUseCasePort updatePaymentStatusUseCasePort){
        return new PaymentController(paymentWebhookUseCasePort, createPaymentUseCasePort, getPaymentByOrderUseCasePort, updatePaymentStatusUseCasePort);
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
    public CreatePaymentUseCasePort createPaymentUseCasePort(OrderGatewayPort orderGatewayPort, PaymentGatewayPort paymentGatewayPort, OrderPaymentGatewayPort orderPaymentGatewayPort){
        return new CreatePaymentUseCase(orderGatewayPort, paymentGatewayPort, orderPaymentGatewayPort);
    }

    @Bean
    public UpdatePaymentStatusUseCasePort updatePaymentStatusUseCasePort(PaymentWebhookUseCase paymentWebhookUseCase, OrderPaymentGatewayPort orderPaymentGatewayPort){
        return new UpdatePaymentStatusUseCase(orderPaymentGatewayPort, paymentWebhookUseCase);
    }

    @Bean
    public GetPaymentByOrderUseCasePort getPaymentByOrderUseCasePort( PaymentGatewayPort paymentGatewayPort, OrderPaymentGatewayPort orderPaymentGatewayPort){
        return new GetPaymentByOrderUseCase(paymentGatewayPort, orderPaymentGatewayPort);
    }

    @Bean
    public PaymentWebhookUseCasePort paymentWebhookUseCasePort(OrderPaymentGatewayPort paymentGatewayPort, OrderGatewayPort orderGatewayPort) {
        return new PaymentWebhookUseCase(paymentGatewayPort, orderGatewayPort);
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
