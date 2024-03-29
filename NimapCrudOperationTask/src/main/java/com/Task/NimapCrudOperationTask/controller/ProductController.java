package com.Task.NimapCrudOperationTask.controller;

import com.Task.NimapCrudOperationTask.ProductService;
import com.Task.NimapCrudOperationTask.model.Products;
import com.Task.NimapCrudOperationTask.repo.ProductServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create operation
    @PostMapping
    public String createProduct(@RequestBody Products product) {
        Products savedProduct = productService.saveProduct(product);
        return "Product inserted successfully";
    }

    // Read operation (Get product by ID)
    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id) {
        Optional<Products> optionalProduct = productService.getProductById(id);
        return optionalProduct.orElse(null); // Spring handles null return as 404
    }


    ///pagination with Read operation (Get all products)
    @GetMapping
    public Object getProductById(@RequestParam(name = "page", required = false) Integer page) {
        int pageSize = 2; // Default page size

        int totalProducts = productService.getAllProducts().size();
        int totalPages = (int) Math.ceil((int) totalProducts / pageSize);

        if(page != null){
            if(page < 1 || page > totalPages){
                return "Invalid page number";
            } else {
                PageRequest pageRequest = PageRequest.of(page - 1, pageSize); // Page numbers start from 0
                Page<Products> productPage = productService.getPaginatedProducts(pageRequest);


                int  remainingPages = totalPages - page;
                return new ProductPageResponse(page, productPage.getContent(), remainingPages);
            }
        }
        else {
            List<Products> products = productService.getAllProducts();
            return products;
        }

    }


    // Custom response object to include page number and products for pagination
    static class ProductPageResponse {
        private int pageNumber;
        private List<Products> products;
        private int remainingPages;

        public ProductPageResponse(int pageNumber, List<Products> products, int remainingPages) {
            this.pageNumber = pageNumber;
            this.products = products;
            this.remainingPages = remainingPages;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public List<Products> getProducts() {
            return products;
        }

        public int getRemainingPages() {
            return remainingPages;
        }
    }


    // Update operation using ID
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Products product) {
        Optional<Products> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            Products existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName()); // Update product's name
            existingProduct.setPrice(product.getPrice()); // Update product's price
            existingProduct.setCategory(product.getCategory()); // Update product's category
            productService.saveProduct(existingProduct);
            return "Product updated successfully";
        } else {
            return null; // Spring handles null return as 404
        }
    }

    // Delete operation using ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Optional<Products> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            productService.deleteProductById(id);
            return "Product deleted successfully";
        } else {
            return null; // Spring handles null return as 404
        }
    }
}
