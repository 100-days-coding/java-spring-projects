package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.dto.BookRequest;
import com.example.gestionbiblioteca.entity.Author;
import com.example.gestionbiblioteca.entity.Book;
import com.example.gestionbiblioteca.entity.Category;
import com.example.gestionbiblioteca.service.AuthorService;
import com.example.gestionbiblioteca.service.BookService;
import com.example.gestionbiblioteca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getBooks();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getById(@PathVariable Long bookId) {
        Optional<Book> book = bookService.getBooksId(bookId);
        return book.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookRequest bookRequest) {
        try {
            Book book = new Book();
            book.setBookTitle(bookRequest.getBookTitle());
            book.setBookYear(bookRequest.getBookYear());

            // Enlazar el autor existente con el libro
            Author author = authorService.getAuthorId(bookRequest.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(author);

            // Enlazar categorías si se proporcionan
            if (bookRequest.getCategoryId() != null && !bookRequest.getCategoryId().isEmpty()) {
                List<Category> categories = new ArrayList<>();
                for (Long categoryId : bookRequest.getCategoryId()) {
                    Category category = categoryService.getCategoryId(categoryId)
                            .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
                    categories.add(category);
                }
                book.setCategories(categories);
            }

            Book savedBook = bookService.saveOrUpdate(book);
            return ResponseEntity.ok(savedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> update(@PathVariable Long bookId, 
                                     @RequestBody BookRequest bookRequest) {
        try {
            Optional<Book> existingBookOpt = bookService.getBooksId(bookId);
            if (existingBookOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Book book = existingBookOpt.get();
            book.setBookTitle(bookRequest.getBookTitle());
            book.setBookYear(bookRequest.getBookYear());

            // Actualizar autor
            Author author = authorService.getAuthorId(bookRequest.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(author);

            // Actualizar categorías
            if (bookRequest.getCategoryId() != null) {
                List<Category> categories = new ArrayList<>();
                for (Long categoryId : bookRequest.getCategoryId()) {
                    Category category = categoryService.getCategoryId(categoryId)
                            .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
                    categories.add(category);
                }
                book.setCategories(categories);
            } else {
                book.setCategories(new ArrayList<>());
            }

            Book updatedBook = bookService.saveOrUpdate(book);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable Long bookId) {
        try {
            if (bookService.getBooksId(bookId).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            bookService.deleteById(bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoint adicional: buscar libros por autor
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) {
        List<Book> books = bookService.getBooksByAuthor(authorId);
        return ResponseEntity.ok(books);
    }

    // Endpoint adicional: buscar libros por categoría
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Long categoryId) {
        List<Book> books = bookService.getBooksByCategory(categoryId);
        return ResponseEntity.ok(books);
    }
}


