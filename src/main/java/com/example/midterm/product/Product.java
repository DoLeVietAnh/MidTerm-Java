package com.example.midterm.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record Product(
                      Integer id,
                      String name,
                      Float price,
                      Float discounted_price,
                      String image,
                      String description,
                      Category category
                      ) {
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        if (price - discounted_price < 0) {
            throw new IllegalArgumentException("Discounted price must be smaller than price");
        }

//        if(discounted_price == null) {
//            discounted_price = BigDecimal.ZERO;
//        } else {
//            discounted_price = discounted_price;
//        }
     }
}
