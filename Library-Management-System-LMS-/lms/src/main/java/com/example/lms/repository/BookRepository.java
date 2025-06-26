package com.example.lms.repository;

import com.example.lms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<com.example.lms.model.Book> findByTitleContainingOrAuthorContaining(String query, String query1);
}