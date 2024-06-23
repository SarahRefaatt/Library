//package com.example.demo;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.verify;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.example.demo.entity.Book;
//import com.example.demo.exception.NoSuchElementException;
//import com.example.demo.repository.BookRepository;
//import com.example.demo.service.BookService;
//
//public class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddBook() {
//        Book book = new Book();
//        book.setTitle("Test Book");
//        book.setAuthor("Test Author");
//        book.setISBN("1234567890");
//
//        bookService.addBook(book);
//
//        verify(bookRepository).save(book);
//    }
//
//    @Test
//    void testGetBooks() {
//        List<Book> books = new ArrayList<>();
//        books.add(new Book("Book 1", "Author 1", "ISBN1"));
//        books.add(new Book("Book 2", "Author 2", "ISBN2"));
//
//        doReturn(books).when(bookRepository).findAll();
//
//        List<Book> result = bookService.getBooks();
//
//        assertThat(result).isEqualTo(books);
//    }
//
//    @Test
//    void testGetBookById() throws NoSuchElementException {
//        Book book = new Book("Book 1", "Author 1",  "ISBN1");
//        book.setId(1L);
//
//        doReturn(Optional.of(book)).when(bookRepository).findById(1L);
//
//        Book result = bookService.getBookById(1L);
//
//        assertThat(result).isEqualTo(book);
//    }
//
//    @Test
//    void testGetBookByIdNotFound() {
//        doReturn(Optional.empty()).when(bookRepository).findById(1L);
//
//        assertThrows(NoSuchElementException.class, () -> bookService.getBookById(1L));
//    }
//
//    @Test
//    void testUpdateBook() {
//        Book existingBook = new Book("Book 1", "Author 1",  "ISBN1");
//        existingBook.setId(1L);
//
//        Book updatedBook = new Book("Updated Book", "Updated Author",  "Updated ISBN");
//        updatedBook.setId(1L);
//
//        doReturn(existingBook).when(bookRepository).getById(1L);
//        doReturn(updatedBook).when(bookRepository).save(existingBook);
//
//        Book result = bookService.updateBook(updatedBook);
//
//        assertThat(result).isEqualTo(updatedBook);
//        verify(bookRepository).save(existingBook);
//    }
//
//    @Test
//    void testDeleteBook() {
//        doNothing().when(bookRepository).deleteById(1L);
//
//        bookService.deleteBook(1L);
//
//        verify(bookRepository).deleteById(1L);
//    }
//}
//
//
//
///* @Mock
//    BookRepository bookRepository;
//
//    AutoCloseable autoCloseable;
//
//    BookService underTest;
//
//    @BeforeEach
//    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
//        underTest = new BookService(bookRepository);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }
//
//    @Test
//    @Disabled
//    void canAddBook() {
//        String title = "it";
//        Book book = new Book("it", "Amany", "1234594321123");
//
//        underTest.addBook(book);
//
//        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
//        verify(bookRepository).save(bookArgumentCaptor.capture());
//
//        Book capturedBook = bookArgumentCaptor.getValue();
//        assertThat(capturedBook).isEqualTo(book);
//    }
//
//    @Test
//    void canGetBooks() {
//        underTest.getBooks();
//        verify(bookRepository).findAll();
//    }
//
//    // These methods are missing their annotations and implementations.
//    // You should add them according to the functionality you want to test.
//
//    // @Test
//    // @Disabled
//    // public Book getBookById(Long id) {
//    // }
//
//    // @Test
//    // @Disabled
//    // public Book updateBook(Book book) {
//    // }
//
//    // @Test
//    // @Disabled
//    // public void deleteBook(Long id) {
//    // }*/