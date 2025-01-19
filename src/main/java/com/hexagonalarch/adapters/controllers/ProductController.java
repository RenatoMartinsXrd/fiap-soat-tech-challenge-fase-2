package com.hexagonalarch.adapters.controllers;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.usecases.Product.CreateProductUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetAllProductsUseCasePort;
import com.hexagonalarch.core.ports.usecases.Product.GetProductUseCasePort;

import java.util.List;

public class ProductController {

    private final CreateProductUseCasePort createProductUseCase;
    private final GetProductUseCasePort getProductUseCase;
    private final GetAllProductsUseCasePort getAllProductsUseCase;

    public ProductController(CreateProductUseCasePort createProductUseCase, GetProductUseCasePort getProductUseCase, GetAllProductsUseCasePort getAllProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
    }

    public Product createProduct(Product product) {
        return createProductUseCase.createProduct(product);
    }

    public Product getProductById(Long id) {
        return getProductUseCase.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return getAllProductsUseCase.getAllProducts();
    }
}