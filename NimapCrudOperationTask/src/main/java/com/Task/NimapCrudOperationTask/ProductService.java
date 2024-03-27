package com.Task.NimapCrudOperationTask;

import com.Task.NimapCrudOperationTask.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface ProductService {
    Products saveProduct(Products product);
    Optional<Products> getProductById(Long id);
    List<Products> getAllProducts();
    void deleteProductById(Long id);

    public Page<Products> getPaginatedProducts(Pageable pageable);

}
