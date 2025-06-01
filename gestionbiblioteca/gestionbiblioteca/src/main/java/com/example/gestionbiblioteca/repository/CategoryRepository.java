package com.example.gestionbiblioteca.repository;

import com.example.gestionbiblioteca.entity.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Buscar categorías por nombre que contenga texto
    List<Category> findByCategoryNameContainingIgnoreCase(String name);
    
    // Consulta personalizada: categorías con más de N libros
    @Query("SELECT c FROM Category c WHERE SIZE(c.books) > :minBooks")
    List<Category> findCategoriesWithMoreThanNBooks(@Param("minBooks") int minBooks);
    
    // Consulta personalizada: categorías que tienen libros
    @Query("SELECT DISTINCT c FROM Category c WHERE SIZE(c.books) > 0")
    List<Category> findCategoriesWithBooks();
}
