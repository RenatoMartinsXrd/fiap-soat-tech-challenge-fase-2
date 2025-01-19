package com.hexagonalarch.frameworks.jpa;

import com.hexagonalarch.adapters.presenters.ProductConverter;
import com.hexagonalarch.frameworks.jpa.entity.CategoryEntity;
import com.hexagonalarch.frameworks.jpa.entity.ProductEntity;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.ports.gateways.ProductGatewayPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaProductRepositoryAdapter implements ProductGatewayPort {

    private final JpaProductRepository jpaRepository;
    private final ModelMapper modelMapper;
    private final JpaCategoryRepository jpaCategoryRepository;
    private final ProductConverter productConverter;

    @Override
    public Product save(Product product) {
        CategoryEntity categoryEntity = jpaCategoryRepository.findByName(product.getCategory().getValue())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);

        productEntity.setCategory(categoryEntity);

        ProductEntity savedProductEntity = jpaRepository.save(productEntity);

        return productConverter.entityToDomain(savedProductEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<ProductEntity> optionalProductEntity = jpaRepository.findById(id);
        return optionalProductEntity.map(productEntity -> productConverter.entityToDomain(optionalProductEntity.get()));
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream()
                .map(productConverter::entityToDomain)
                .toList();
    }
}

