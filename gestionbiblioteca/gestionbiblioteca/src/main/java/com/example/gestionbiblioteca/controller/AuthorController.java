package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.entity.Author;
import com.example.gestionbiblioteca.entity.Book;
import com.example.gestionbiblioteca.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAll(){
        return authorService.getAuthors();
    }

    @GetMapping("/{authorId}")
    public Optional<Author> getById(@PathVariable("authorId") Long authorId){
        return authorService.getAuthorId(authorId);
    }

    @PostMapping
    public Author saveUpdate(@RequestBody Author author){
        authorService.saveOrUpdate(author);
        return author;

    }
}
