package com.hexagonalarch.frameworks.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;

}