package com.example.gestionbiblioteca.repository;

import com.example.gestionbiblioteca.entity.Book; 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
     // Buscar libros por autor
    List<Book> findByAuthor_AuthorId(Long authorId);
    
    // Buscar libros por categoría
    List<Book> findByCategories_CategoryId(Long categoryId);
    
    // Buscar libros por año
    List<Book> findByBookYear(Integer year);
    
    // Buscar libros por título que contenga texto (ignorando mayúsculas/minúsculas)
    List<Book> findByBookTitleContainingIgnoreCase(String title);
    
    // Buscar libros por rango de años
    List<Book> findByBookYearBetween(Integer startYear, Integer endYear);
    
    // Consulta personalizada: libros con más de N categorías
    @Query("SELECT b FROM Book b WHERE SIZE(b.categories) > :minCategories")
    List<Book> findBooksWithMoreThanNCategories(@Param("minCategories") int minCategories);
    
    // Consulta personalizada: libros por nacionalidad del autor
    @Query("SELECT b FROM Book b WHERE b.author.nationality = :nationality")
    List<Book> findBooksByAuthorNationality(@Param("nationality") String nationality);

}
