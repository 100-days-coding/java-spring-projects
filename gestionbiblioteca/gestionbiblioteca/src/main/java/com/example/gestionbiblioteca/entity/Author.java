package com.example.gestionbiblioteca.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "authors")
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(nullable = false)
    private String authorName;

    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("author-books")
    private List<Book> books;
}
