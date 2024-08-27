package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
public class ProductController {

    @GetMapping()
    public List<Product> listProducts(){
        //TODO: call product service
        return null;
    } 


}
