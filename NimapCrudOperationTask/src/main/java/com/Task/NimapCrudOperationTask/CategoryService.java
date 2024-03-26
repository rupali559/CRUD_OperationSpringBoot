package com.Task.NimapCrudOperationTask;

import com.Task.NimapCrudOperationTask.model.Category;
import com.Task.NimapCrudOperationTask.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategory();


    void updateCategory(Long id, Category updatedCategory);

    void deleteCategory(Long id);

    Category findByName(String name);

    Page<Category> getPaginatedCategories(Pageable pageable);
}
