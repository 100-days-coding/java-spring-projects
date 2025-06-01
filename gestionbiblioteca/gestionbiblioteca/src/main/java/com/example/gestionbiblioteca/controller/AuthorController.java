package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.entity.Author;

import com.example.gestionbiblioteca.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Author> getById(@PathVariable("authorId") Long authorId){
        Optional<Author> author = authorService.getAuthorId(authorId);
        return author.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author){
        try {
            Author savedAuthor = authorService.saveOrUpdate(author);
            return ResponseEntity.ok(savedAuthor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Author> update(@PathVariable("authorId") Long authorId, 
                                       @RequestBody Author authorDetails) {
        try {
            Optional<Author> existingAuthor = authorService.getAuthorId(authorId);
            if (existingAuthor.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Author author = existingAuthor.get();
            author.setAuthorName(authorDetails.getAuthorName());
            author.setNationality(authorDetails.getNationality());

            Author updatedAuthor = authorService.saveOrUpdate(author);
            return ResponseEntity.ok(updatedAuthor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> delete(@PathVariable("authorId") Long authorId) {
        try {
            if (authorService.getAuthorId(authorId).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            authorService.deleteById(authorId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
