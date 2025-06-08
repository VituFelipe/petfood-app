package com.petfood.catalog_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id; // MongoDB troquei pq o mysql to usando pro trampo
    private String name;
    private String type;
    private String description;
    private Double weight;
    private Double price;
    private Boolean available;
}
