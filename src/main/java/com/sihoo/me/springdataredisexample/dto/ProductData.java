package com.sihoo.me.springdataredisexample.dto;

import com.github.dozermapper.core.Mapping;
import com.sihoo.me.springdataredisexample.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductData {
    @Mapping("price")
    private Integer price;
    @Mapping("name")
    private String name;

    public static Product toEntity(Long id, Integer price, String name) {
        return Product.builder()
                .id(id)
                .price(price)
                .name(name)
                .build();
    }
}
