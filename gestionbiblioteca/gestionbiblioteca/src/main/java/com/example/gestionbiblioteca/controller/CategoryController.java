package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.entity.Author;
import com.example.gestionbiblioteca.entity.Category;
import com.example.gestionbiblioteca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Category> getById(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategoryId(categoryId);
    }

    @PostMapping
    public Category saveUpdate(@RequestBody Category category) {
        categoryService.saveOrUpdate(category);
        return category;
    }
}
