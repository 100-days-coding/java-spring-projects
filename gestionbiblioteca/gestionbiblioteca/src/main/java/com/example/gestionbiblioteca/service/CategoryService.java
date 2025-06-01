package com.example.gestionbiblioteca.service;


import com.example.gestionbiblioteca.entity.Category;
import com.example.gestionbiblioteca.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryId(Long id){
        return categoryRepository.findById(id);
    }

    public Category saveOrUpdate(Category category){
        return categoryRepository.save(category);
    }

    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public boolean existsById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    public List<Category> getCategoriesByName(String name) {
        return categoryRepository.findByCategoryNameContainingIgnoreCase(name);
    }
}
