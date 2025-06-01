package com.example.gestionbiblioteca.repository;

import com.example.gestionbiblioteca.entity.Author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Buscar autores por nacionalidad
    List<Author> findByNationality(String nationality);
    
    // Buscar autores por nombre que contenga texto
    List<Author> findByAuthorNameContainingIgnoreCase(String name);
    
    // Consulta personalizada: autores con más de N libros
    @Query("SELECT a FROM Author a WHERE SIZE(a.books) > :minBooks")
    List<Author> findAuthorsWithMoreThanNBooks(@Param("minBooks") int minBooks);
    
    // Consulta personalizada: autores que tienen libros
    @Query("SELECT DISTINCT a FROM Author a WHERE SIZE(a.books) > 0")
    List<Author> findAuthorsWithBooks();
}
