package com.petfood.catalog_service.repository;

import com.petfood.catalog_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
