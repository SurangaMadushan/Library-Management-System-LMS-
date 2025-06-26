package com.example.lms.service;

import com.example.lms.model.BorrowRecord;

import java.util.List;

public interface BorrowService {
    BorrowRecord borrowBook(Long userId, Long bookId);

    BorrowRecord returnBook(Long borrowId);

    List<BorrowRecord> getUserBorrowHistory(Long userId);

    List<BorrowRecord> getBookBorrowHistory(Long bookId);
}