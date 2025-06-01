package com.example.gestionbiblioteca.repository;

import com.example.gestionbiblioteca.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


}
