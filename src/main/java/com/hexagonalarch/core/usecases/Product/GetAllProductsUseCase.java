package com.hexagonalarch.core.usecases.Product;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Product.GetAllProductsUseCasePort;

import java.util.List;

public class GetAllProductsUseCase implements GetAllProductsUseCasePort {
    private ProductGatewayPort productGatewayPort;

    public GetAllProductsUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public List<Product> getAllProducts() {
        return productGatewayPort.findAll();
    }
}
