package com.example.gestionbiblioteca.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private String bookTitle;
    private Integer bookYear;
    private Long authorId;
    private List<Long> categoryId;
}
