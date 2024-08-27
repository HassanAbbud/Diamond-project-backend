package com.blueweb.springboot.diamond_project.backend.diamond_project_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String description;
    private Number price;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Number getPrice() {
        return price;
    }
    public void setPrice(Number price) {
        this.price = price;
    }
    

    // id?: string;
    // code?: string;
    // name?: string;
    // description?: string;
    // price?: number;
    // quantity?: number;
    // inventoryStatus?: InventoryStatus;
    // category?: string;
    // image?: string;
    // rating?: number;

}
