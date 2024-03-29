package com.Task.NimapCrudOperationTask.service;

import com.Task.NimapCrudOperationTask.ProductService;
import com.Task.NimapCrudOperationTask.model.Products;
import com.Task.NimapCrudOperationTask.repo.ProductServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;



import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductServiceRepo productServiceRepo;

    @Autowired
    public ProductServiceImpl(ProductServiceRepo productServiceRepo) {
        this.productServiceRepo = productServiceRepo;
    }

    @Override
    public Products saveProduct(Products product) {
        return productServiceRepo.save(product);
    }

    @Override
    public Optional<Products> getProductById(Long id) {
        return productServiceRepo.findById(id);
    }

    @Override
    public List<Products> getAllProducts() {
        return productServiceRepo.findAll();
    }

    @Override
    public void deleteProductById(Long id) {

        productServiceRepo.deleteById(id);
    }

    //pagination
    @Override
    public Page<Products> getPaginatedProducts(Pageable pageable) {
        return productServiceRepo.findAll(pageable);
    }
}