package com.Task.NimapCrudOperationTask.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;


//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    //@JsonIgnoreProperties("products")
    @JsonBackReference
    private Category category;

    //@JsonProperty("category_name")
    public String getCategoryName() {
        if (category != null) {
            return category.getName();
        } else {
            return null;
        }
    }

    public Products() {
    }

    public Products(Long id, String name, double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }




}
