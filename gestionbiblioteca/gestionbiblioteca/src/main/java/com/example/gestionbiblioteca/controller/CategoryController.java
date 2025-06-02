package com.example.gestionbiblioteca.controller;


import com.example.gestionbiblioteca.entity.Category;
import com.example.gestionbiblioteca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getCategory();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getById(@PathVariable Long categoryId){
        Optional<Category> category = categoryService.getCategoryId(categoryId);
        return category.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.saveOrUpdate(category);
            return ResponseEntity.ok(savedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable Long categoryId, 
                                         @RequestBody Category categoryDetails) {
        try {
            Optional<Category> existingCategory = categoryService.getCategoryId(categoryId);
            if (existingCategory.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Category category = existingCategory.get();
            category.setCategoryName(categoryDetails.getCategoryName());

            Category updatedCategory = categoryService.saveOrUpdate(category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        try {
            if (categoryService.getCategoryId(categoryId).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            categoryService.deleteById(categoryId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
