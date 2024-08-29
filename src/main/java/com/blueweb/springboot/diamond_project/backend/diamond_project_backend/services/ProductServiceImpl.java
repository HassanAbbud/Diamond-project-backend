package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities.Product;
import com.blueweb.springboot.diamond_project.backend.diamond_project_backend.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired 
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    @Transactional
    public void delete(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
