package com.hexagonalarch.core.usecases.Product;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Product.CreateProductUseCasePort;

public class CreateProductUseCase implements CreateProductUseCasePort {
    private final ProductGatewayPort productGatewayPort;

    public CreateProductUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productGatewayPort.save(product);
    }
}
