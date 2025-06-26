package com.example.lms.controller;

import com.example.lms.model.BorrowRecord;
import com.example.lms.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public BorrowRecord borrowBook(@RequestParam Long userId,
                                   @RequestParam Long bookId) {
        return borrowService.borrowBook(userId, bookId);
    }

    @PutMapping("/return/{id}")
    public BorrowRecord returnBook(@PathVariable Long id) {
        return borrowService.returnBook(id);
    }

    @GetMapping("/user/{userId}")
    public List<BorrowRecord> getUserBorrowHistory(@PathVariable Long userId) {
        return borrowService.getUserBorrowHistory(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<BorrowRecord> getBookBorrowHistory(@PathVariable Long bookId) {
        return borrowService.getBookBorrowHistory(bookId);
    }
}