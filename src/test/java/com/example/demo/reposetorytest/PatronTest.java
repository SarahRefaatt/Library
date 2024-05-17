package com.example.demo.reposetorytest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Book;
import com.example.demo.entity.Patron;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PatronRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PatronTest {

    @Autowired
    PatronRepository patronRepository;

    @Autowired
    BookRepository bookRepository;


    @AfterEach
    void tearDown() {
        patronRepository.deleteAll();;
    }

    Patron patron;
    Book book;
    List<Book> books;


    @BeforeEach
    public void init() {
        book=Book.builder().ISBN("isbn").title("title").author("author").build();

        bookRepository.save(book);
        books=new ArrayList<>();
        books.add(book);

        patron=Patron.builder().name("patron").borrowedBooks(books).build();


    }



    @Test
    public void BookRepositoryTest_Save_ReturnSavedBook() {



        Patron savedPatron=patronRepository.save(patron);

        Assertions.assertThat(savedPatron).isNotNull();

    }


    @Test
    public void PatronRepositoryTest_FindAll_ReturnPatron() {



        List<Patron> savedPatron=patronRepository.findAll();


        Assertions.assertThat(savedPatron).isNotNull();

        Assertions.assertThat(savedPatron.size()).isEqualTo(0);

    }


    @Test
    public void PatronRepositoryTest_FindById_ReturnPatron() {

        patronRepository.save(patron);

        Patron savedPatron=patronRepository.findById(patron.getId()).get();


        Assertions.assertThat(savedPatron).isNotNull();

    }


    @Test
    public void PatronRepositoryTest_UpdatePatron_ReturnUpdatedPatron() {


        patronRepository.save(patron);

        Patron savedPatron=patronRepository.findById(patron.getId()).get();

        savedPatron.setName("lolooo");
        //patronRepository.save(savedPatron);




        Assertions.assertThat(savedPatron).isNotNull();

        Assertions.assertThat(savedPatron.getName()).isEqualTo("lolooo");

    }







}
