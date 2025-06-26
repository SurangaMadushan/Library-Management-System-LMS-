package com.example.lms.service;

import com.example.lms.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);

    Book getBookById(Long id);

    List<Book> getAllBooks();

    List<Book> searchBooks(String query);

    Book updateBook(Long id, Book book) throws InterruptedException;

    void deleteBook(Long id);
}