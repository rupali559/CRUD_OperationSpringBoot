package com.Task.NimapCrudOperationTask.repo;

import com.Task.NimapCrudOperationTask.ProductService;
import com.Task.NimapCrudOperationTask.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductServiceRepo extends JpaRepository<Products, Long> {
}
