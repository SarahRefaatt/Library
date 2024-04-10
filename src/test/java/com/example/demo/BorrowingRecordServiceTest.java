package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowingRecord;
import com.example.demo.entity.Patron;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.PatronRepository;
import com.example.demo.service.BorrowingRecordService;

public class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBook() {
        Book book = new Book();
        book.setId(1L);
        Patron patron = new Patron();
        patron.setId(1L);

        doReturn(Optional.of(book)).when(bookRepository).findById(1L);
        doReturn(Optional.of(patron)).when(patronRepository).findById(1L);

        borrowingRecordService.borrowBook(1L, 1L);

        verify(borrowingRecordRepository).save(any(BorrowingRecord.class));
        verify(patronRepository).save(patron);
    }

    @Test
    void testReturnBook() {
        Book book = new Book();
        book.setId(1L);
        Patron patron = new Patron();
        patron.setId(1L);

        List<Book> borrowedBooks = new ArrayList<>();
        borrowedBooks.add(book);
        patron.setBorrowedBooks(borrowedBooks);

        doReturn(Optional.of(patron)).when(patronRepository).findById(1L);
        doReturn(new ArrayList<BorrowingRecord>()).when(borrowingRecordRepository).findALLByBookId(1L);

        borrowingRecordService.returnBook(1L, 1L);

        verify(patronRepository).save(patron);
        verify(borrowingRecordRepository).deleteAll(any());
    }
}
