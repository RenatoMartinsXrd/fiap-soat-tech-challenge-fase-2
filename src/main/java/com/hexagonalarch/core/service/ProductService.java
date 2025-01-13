package com.hexagonalarch.core.service;

import com.hexagonalarch.core.ports.in.Product.CreateProductUseCase;
import com.hexagonalarch.core.ports.in.Product.GetAllProductsUseCase;
import com.hexagonalarch.core.ports.in.Product.GetProductUseCase;
import com.hexagonalarch.core.ports.out.ProductRepositoryPort;
import com.hexagonalarch.core.domain.Product;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase, GetAllProductsUseCase {

    private final ProductRepositoryPort productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product storedProduct = product.orElseThrow(() -> new RuntimeException("Product not found"));
        return storedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
