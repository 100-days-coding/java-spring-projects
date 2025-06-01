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

    public Optional<Book> getBooksId(Long id){
        return bookRepository.findById(id);
    }

    public Book saveOrUpdate(Book book){
        bookRepository.save(book);

        return book;
    }
}
