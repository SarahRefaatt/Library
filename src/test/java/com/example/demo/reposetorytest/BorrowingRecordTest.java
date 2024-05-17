package com.example.demo.reposetorytest;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowingRecord;
import com.example.demo.entity.Patron;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.PatronRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BorrowingRecordTest {

    @Autowired
    PatronRepository patronRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;



    @AfterEach
    void tearDown() {
        borrowingRecordRepository.deleteAll();;
    }

    Patron patron;
    Book book;
    List<Book> books;
    BorrowingRecord borrowingRecord;


    @BeforeEach
    public void init() {
        book=Book.builder().ISBN("isbn").title("title").author("author").build();

        bookRepository.save(book);
        books=new ArrayList<>();
        books.add(book);

        patron=Patron.builder().name("patron").borrowedBooks(books).build();
        patronRepository.save(patron);

        borrowingRecord=BorrowingRecord.builder().bookId(book.getId()).patronId(patron.getId()).build();


    }



    @Test
    public void BorrowingRecordRepositoryTest_Save_ReturnSavedBook() {


        BorrowingRecord savedBorrowingRecord=borrowingRecordRepository.save(borrowingRecord);

        Assertions.assertThat(borrowingRecord).isNotNull();

    }


    @Test
    public void BorrowingRecordRepositoryTest_FindAll_ReturnPatron() {



        List<BorrowingRecord> savedborrowingRecord=borrowingRecordRepository.findAll();


        Assertions.assertThat(savedborrowingRecord).isNotNull();

        Assertions.assertThat(savedborrowingRecord.size()).isEqualTo(0);

    }


    @Test
    public void BorrowingRecordRepositoryTest_FindById_ReturnPatron() {

        borrowingRecordRepository.save(borrowingRecord);

        BorrowingRecord savedborrowingRecord=borrowingRecordRepository.findById(borrowingRecord.getId()).get();


        Assertions.assertThat(savedborrowingRecord).isNotNull();

    }


    @Test
    public void BorrowingRecordRepositoryTest_UpdatePatron_ReturnUpdatedPatron() {


        borrowingRecordRepository.save(borrowingRecord);

        BorrowingRecord savedborrowingRecord=borrowingRecordRepository.findById(borrowingRecord.getId()).get();

        savedborrowingRecord.setBookId(9L);
        //patronRepository.save(savedPatron);




        Assertions.assertThat(savedborrowingRecord).isNotNull();

        Assertions.assertThat(savedborrowingRecord.getBookId()).isEqualTo(9L);

    }







}
