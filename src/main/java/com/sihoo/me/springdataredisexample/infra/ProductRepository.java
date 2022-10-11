package com.sihoo.me.springdataredisexample.infra;

import com.sihoo.me.springdataredisexample.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
