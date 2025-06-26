package com.example.lms.service.Impl;

import com.example.lms.model.Book;
import com.example.lms.model.BorrowRecord;
import com.example.lms.model.User;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.BorrowRecordRepository;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BorrowRecord borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableQuantity() <= 0) {
            throw new RuntimeException("Book not available");
        }

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        record.setReturned(false);

        return borrowRecordRepository.save(record);
    }

    @Override
    public BorrowRecord returnBook(Long borrowId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (record.isReturned()) {
            throw new RuntimeException("Book already returned");
        }

        Book book = record.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepository.save(book);

        record.setReturnDate(LocalDate.now());
        record.setReturned(true);

        return borrowRecordRepository.save(record);
    }

    @Override
    public List<BorrowRecord> getUserBorrowHistory(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }

    @Override
    public List<BorrowRecord> getBookBorrowHistory(Long bookId) {
        return borrowRecordRepository.findByBookId(bookId);
    }
}