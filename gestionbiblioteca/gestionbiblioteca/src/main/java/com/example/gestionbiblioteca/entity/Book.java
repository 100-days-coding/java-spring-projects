package com.example.gestionbiblioteca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookTitle;
    private Integer bookYear;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "category_book",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn (name = "categoryId")
    )
    private List<Category> categories;
}
