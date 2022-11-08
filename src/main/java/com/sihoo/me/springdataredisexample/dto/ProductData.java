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

    public Product toEntity() {
        return Product.builder()
                .price(this.price)
                .name(this.name)
                .build();
    }
}
