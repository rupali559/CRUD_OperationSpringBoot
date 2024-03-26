package com.Task.NimapCrudOperationTask.controller;


import com.Task.NimapCrudOperationTask.CategoryService;
import com.Task.NimapCrudOperationTask.model.Category;
import com.Task.NimapCrudOperationTask.repo.CategoryServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create operation
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        Category existingCategory = categoryService.findByName(category.getName());

        if (existingCategory != null) {
            return ResponseEntity.badRequest().body("Category already exists");
        }

        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok("Category inserted successfully");
    }

    //Read operation (Get all categories)
//    @GetMapping
//    public List<Category> getAllCategoriesDetails() {
//        return categoryService.getAllCategory();
//    }



    // Read operation (Get category by ID)
    @GetMapping
    public Object getCategoryById(@RequestParam(name = "page", required = false) Integer page) {
        int pageSize = 2; // Default page size

        int totalProducts = categoryService.getAllCategory().size();
        int totalPages = (int) Math.ceil((int) totalProducts / pageSize);

        if(page != null){
            if(page < 1 || page > totalPages){
                return "Invalid page number";
            } else {
                PageRequest pageRequest = PageRequest.of(page - 1, pageSize); // Page numbers start from 0
                Page<Category> productPage = categoryService.getPaginatedCategories(pageRequest);


                int  remainingPages = totalPages - page;
                return new CategoryController.ProductPageResponse(page, productPage.getContent(), remainingPages);
            }
        }
        else {
            List<Category> category = categoryService.getAllCategory();
            return category;
        }

    }


    // Custom response object to include page number and products
    static class ProductPageResponse {
        private int pageNumber;
        private List<Category> category;
        private int remainingPages;

        public ProductPageResponse(int pageNumber, List<Category> category, int remainingPages) {
            this.pageNumber = pageNumber;
            this.category = category;
            this.remainingPages = remainingPages;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public List<Category> getCategory() {
            return category;
        }

        public int getRemainingPages() {
            return remainingPages;
        }
    }

    //get category by id
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        return optionalCategory.orElse(null); // Spring handles null return as 404
    }


    // Update operation using id
    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(categoryDetails.getName()); // Update category's name
            categoryService.createCategory(existingCategory);
            return "Category updated successfully";
        } else {
            return null; // Spring handles null return as 404
        }
    }

    // Delete operation using id
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        if (optionalCategory.isPresent()) {
            categoryService.deleteCategory(id);
            return "Category deleted successfully";
        } else {
            return null; // Spring handles null return as 404
        }
    }
}
