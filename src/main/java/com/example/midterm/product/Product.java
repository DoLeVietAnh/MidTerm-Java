package com.example.midterm.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record Product(
                      @NotNull Integer id,
                      String name,
                      BigDecimal price,
                      BigDecimal discounted_price,
                      String image,
                      String description,
                      Category category
                      ) {
    public Product {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (discounted_price.compareTo(price) > 0) {
            throw new IllegalArgumentException("Discounted price must be smaller than price");
        }

    }
}
