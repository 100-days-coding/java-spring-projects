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

    @Column(nullable = false)
    private String bookTitle;

    private Integer bookYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId", nullable = false)
    @JsonBackReference("author-books")
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "category_book",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn (name = "categoryId")
    )
    @JsonBackReference("book-categories")
    private List<Category> categories;
}
