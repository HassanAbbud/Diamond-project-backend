package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Product;

public interface ProductService {
    List<Product> findAllProducts();

    Product save(Product product);

    void delete(Product product);
}
