package com.sihoo.me.springdataredisexample.controllers;

import com.sihoo.me.springdataredisexample.applications.ProductService;
import com.sihoo.me.springdataredisexample.domain.Product;
import com.sihoo.me.springdataredisexample.dto.ProductData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product detail(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> list() {
        return productService.getProducts();
    }

    @PostMapping
    public Product create(@RequestBody ProductData productData) {
        return productService.createProduct(productData);
    }

    @PutMapping  ("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody ProductData productData) {
        return productService.updateProduct(id, productData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}

