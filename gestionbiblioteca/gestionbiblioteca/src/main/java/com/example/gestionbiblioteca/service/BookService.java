package com.example.gestionbiblioteca.service;

import com.example.gestionbiblioteca.entity.Book;
import com.example.gestionbiblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBooksId(Long bookId){
        return bookRepository.findById(bookId);
    }

    public Book saveOrUpdate(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public boolean existsById(Long bookId) {
        return bookRepository.existsById(bookId);
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthor_AuthorId(authorId);
    }

    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategories_CategoryId(categoryId);
    }

    public List<Book> getBooksByYear(Integer year) {
        return bookRepository.findByBookYear(year);
    }

    public List<Book> getBooksByTitleContaining(String title) {
        return bookRepository.findByBookTitleContainingIgnoreCase(title);
    }
}
