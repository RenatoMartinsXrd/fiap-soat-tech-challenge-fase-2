package com.hexagonalarch.adapters.inbound;

import com.hexagonalarch.adapters.converters.GenericConverter;
import com.hexagonalarch.adapters.converters.ProductConverter;
import com.hexagonalarch.adapters.dto.request.CreateProductRequest;
import com.hexagonalarch.adapters.dto.response.CreateProductResponse;
import com.hexagonalarch.adapters.dto.response.GetProductResponse;
import com.hexagonalarch.adapters.inbound.ServicesFacade.ProductServiceFacade;
import com.hexagonalarch.core.domain.Product;
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
public class ProductController {

    private final ProductServiceFacade productServiceFacade;
    private final GenericConverter genericConverter;
    private final ProductConverter productConverter;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse createProduct(@Valid @RequestBody CreateProductRequest productRequest) {
        Product productInput = productConverter.toDomain(productRequest);

        Product newProduct = productServiceFacade.createProduct(productInput);

        return genericConverter.toDto(newProduct, CreateProductResponse.class);
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductResponse getProductById(@PathVariable Long id) {
        Product product = productServiceFacade.getProductById(id);
        return genericConverter.toDto(product, GetProductResponse.class);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductResponse> getAllProducts() {
        return productServiceFacade.getAllProducts()
                .stream()
                .map(product -> genericConverter.toDto(product, GetProductResponse.class))
                .toList();
    }
}
