package com.hexagonalarch.adapters.inbound.ServicesFacade;

import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.in.Product.CreateProductUseCase;
import com.hexagonalarch.core.ports.in.Product.GetAllProductsUseCase;
import com.hexagonalarch.core.ports.in.Product.GetProductUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceFacade {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;

    public ProductServiceFacade(CreateProductUseCase createProductUseCase, GetProductUseCase getProductUseCase, GetAllProductsUseCase getAllProductsUseCase) {
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