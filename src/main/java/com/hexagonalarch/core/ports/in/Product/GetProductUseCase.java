package com.hexagonalarch.core.ports.in.Product;

import com.hexagonalarch.core.domain.Product;

public interface GetProductUseCase {

    Product getProductById(Long id);

}
