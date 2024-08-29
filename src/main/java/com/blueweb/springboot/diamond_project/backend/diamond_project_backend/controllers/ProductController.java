package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Product;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.User;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> listProducts(){
        return productService.findAllProducts();

    } 

    @PostMapping()
    public ResponseEntity<Product> create(@Valid @RequestBody Product product, BindingResult result) {
        //TODO: REGISTRATION PROCESS
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        //TODO: process PUT request
        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody User user){
        //TODO: call service delete (productRepository.deleteById)
        return null;
    }
}
