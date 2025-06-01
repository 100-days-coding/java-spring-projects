package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.dto.BookRequest;
import com.example.gestionbiblioteca.entity.Author;
import com.example.gestionbiblioteca.entity.Book;
import com.example.gestionbiblioteca.entity.Category;
import com.example.gestionbiblioteca.service.AuthorService;
import com.example.gestionbiblioteca.service.BookService;
import com.example.gestionbiblioteca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getBooks();
    }

    @GetMapping("/{bookId}")
    public Optional<Book> getById(@PathVariable("bookId") Long bookId) {
        return bookService.getBooksId(bookId);
    }

    @PostMapping
    public Book saveUpdate(@RequestBody BookRequest bookRequest) {
        // Crear una instancia de Book y asignar los valores desde BookRequest
        Book book = new Book();
        book.setBookTitle(bookRequest.getBookTitle());
        book.setBookYear(bookRequest.getBookYear());

        // Enlazar el autor existente con el libro
        Author author = authorService.getAuthorId(bookRequest.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author no found"));
        book.setAuthor(author);

        /*if (bookRequest.getCategoryId() != null) {
            List<Category> categories = new ArrayList<>();
            for (Long categoryId : bookRequest.getCategoryId()) {
                Category category = categoryService.getCategoryId(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                categories.add(category);

            }
            book.setCategories(categories);
        }*/

        // Guardar o actualizar el libro
        return bookService.saveOrUpdate(book);
    }
}


