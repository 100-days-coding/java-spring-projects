package com.example.gestionbiblioteca.service;

import com.example.gestionbiblioteca.entity.Author;


import com.example.gestionbiblioteca.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorId(Long authorId){
        return authorRepository.findById(authorId);
    }

    public Author saveOrUpdate(Author author){
        return authorRepository.save(author);
    }

    public void deleteById(Long authorId){
        authorRepository.deleteById(authorId);
    }

    public boolean existById(Long authorId){
        return authorRepository.existsById(authorId);
    }
}
