package com.Task.NimapCrudOperationTask.repo;

import com.Task.NimapCrudOperationTask.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface CategoryServiceRepo extends JpaRepository<Category,Long> {
    //category name display in product table
    Category findByName(String name);

//    List<Category> getAllCategories();
}
