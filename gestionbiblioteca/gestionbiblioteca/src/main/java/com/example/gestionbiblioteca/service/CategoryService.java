package com.example.gestionbiblioteca.service;

import com.example.gestionbiblioteca.entity.Book;
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

    public void saveOrUpdate(Category category){
        categoryRepository.save(category);
    }
}
