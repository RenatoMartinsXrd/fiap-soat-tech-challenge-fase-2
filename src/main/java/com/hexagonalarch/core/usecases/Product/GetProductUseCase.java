package com.hexagonalarch.core.usecases.Product;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import com.hexagonalarch.core.ports.usecases.Product.GetProductUseCasePort;

import java.util.Optional;

public class GetProductUseCase implements GetProductUseCasePort {
    private ProductGatewayPort productGatewayPort;

    public GetProductUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productGatewayPort.findById(id);
        Product storedProduct = product.orElseThrow(() -> new RuntimeException("Product not found"));
        return storedProduct;
    }
}
