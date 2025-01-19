package com.hexagonalarch.frameworks.rest.controllers;

import com.hexagonalarch.adapters.controllers.ProductController;
import com.hexagonalarch.adapters.presenters.GenericConverter;
import com.hexagonalarch.adapters.presenters.ProductConverter;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.frameworks.rest.dto.request.CreateProductRequest;
import com.hexagonalarch.frameworks.rest.dto.response.CreateProductResponse;
import com.hexagonalarch.frameworks.rest.dto.response.GetProductResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations related to Products")
public class ProductControllerRest {

    private final ProductController productController;
    private final GenericConverter genericConverter;
    private final ProductConverter productConverter;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse createProduct(@Valid @RequestBody CreateProductRequest productRequest) {
        Product productInput = productConverter.toDomain(productRequest);

        Product newProduct = productController.createProduct(productInput);

        return genericConverter.toDto(newProduct, CreateProductResponse.class);
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductResponse getProductById(@PathVariable Long id) {
        Product product = productController.getProductById(id);
        return genericConverter.toDto(product, GetProductResponse.class);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductResponse> getAllProducts() {
        return productController.getAllProducts()
                .stream()
                .map(product -> genericConverter.toDto(product, GetProductResponse.class))
                .toList();
    }
}
