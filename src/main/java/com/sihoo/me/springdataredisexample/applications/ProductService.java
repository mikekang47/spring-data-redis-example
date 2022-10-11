package com.sihoo.me.springdataredisexample.applications;

import com.sihoo.me.springdataredisexample.domain.Product;
import com.sihoo.me.springdataredisexample.dto.ProductData;
import com.sihoo.me.springdataredisexample.error.UserCausedException;
import com.sihoo.me.springdataredisexample.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Cacheable(key = "#productId", value = "products")
    public Product getProduct(Long productId) {
        return findProduct(productId);
    }

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
        Product product = ProductData.toEntity(source.getId(), productData.getPrice(), productData.getName());
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new UserCausedException("Product not found. (Product Id: " + productId + ")"));
    }
}
