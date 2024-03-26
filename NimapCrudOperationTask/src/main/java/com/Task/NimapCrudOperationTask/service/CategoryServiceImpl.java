package com.Task.NimapCrudOperationTask.service;

import com.Task.NimapCrudOperationTask.model.Category;
import com.Task.NimapCrudOperationTask.repo.CategoryServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.Task.NimapCrudOperationTask.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private  CategoryServiceRepo categoryServiceRepo;

    @Autowired
    public CategoryServiceImpl(CategoryServiceRepo categoryServiceRepo) {
        this.categoryServiceRepo = categoryServiceRepo;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryServiceRepo.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryServiceRepo.findById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryServiceRepo.findAll();
    }
    @Override
    public Category findByName(String name) {
        return categoryServiceRepo.findByName(name);
    }

    @Override
    public void updateCategory(Long id, Category updatedCategory) {
        Optional<Category> optionalCategory = categoryServiceRepo.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(updatedCategory.getName()); // Update category's name
            categoryServiceRepo.save(existingCategory);
        } else {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryServiceRepo.findById(id);
        if (optionalCategory.isPresent()) {
            categoryServiceRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }
    }
    //pagination
    @Override
    public Page<Category> getPaginatedCategories(Pageable pageable) {

        return categoryServiceRepo.findAll(pageable);
    }
}
