package com.hexagonalarch.core.ports.out;

import com.hexagonalarch.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

}