package com.hexagonalarch.core.ports.in.Product;

import com.hexagonalarch.core.domain.Product;

import java.util.List;

public interface GetAllProductsUseCase {

    List<Product> getAllProducts();

}
