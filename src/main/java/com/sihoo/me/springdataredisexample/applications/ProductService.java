package com.sihoo.me.springdataredisexample.applications;

import com.github.dozermapper.core.Mapper;
import com.sihoo.me.springdataredisexample.domain.Product;
import com.sihoo.me.springdataredisexample.dto.ProductData;
import com.sihoo.me.springdataredisexample.error.UserCausedException;
import com.sihoo.me.springdataredisexample.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Mapper mapper;

    @Cacheable(key = "#productId", value = "products")
    public Product getProduct(Long productId) {
        return findProduct(productId);
    }

    @Cacheable("products")
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductData productData) {
        Product product = Product.builder()
                .name(productData.getName())
                .price(productData.getPrice())
                .build();
        return productRepository.save(product);
    }

    @CachePut(key="#productId", value="products")
    public Product updateProduct(Long productId, ProductData productData) {
        Product source = findProduct(productId);
        source.update(mapper.map(productData, Product.class));
        return source;
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new UserCausedException("Product not found. (Product Id: " + productId + ")", HttpStatus.NOT_FOUND));
    }
}
