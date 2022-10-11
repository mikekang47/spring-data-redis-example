package com.sihoo.me.springdataredisexample.dto;

import com.sihoo.me.springdataredisexample.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductData {
    private Integer price;
    private String name;

    public static Product toEntity(Long id, Integer price, String name) {
        return Product.builder()
                .id(id)
                .price(price)
                .name(name)
                .build();
    }
}
