package com.example.demo.reposetorytest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookTest {

	@Autowired
	BookRepository bookRepository;


	@AfterEach
	void tearDown() {
		bookRepository.deleteAll();;
	}

	@Test
	public void BookRepositoryTest_Save_ReturnSavedBook() {


		Book book=Book.builder().ISBN("isbn").title("title").author("author").build();

		Book savedBook=bookRepository.save(book);


		Assertions.assertThat(savedBook).isNotNull();

	}


	@Test
	public void BookRepositoryTest_FindAll_ReturnBooks() {


		Book book=Book.builder().ISBN("isbn").title("title").author("author").build();
		Book book2=Book.builder().ISBN("isbn").title("title").author("author").build();
		bookRepository.save(book);
		bookRepository.save(book2);


		List<Book> savedBook=bookRepository.findAll();


		Assertions.assertThat(savedBook).isNotNull();
		Assertions.assertThat(savedBook.size()).isEqualTo(2);

	}


	@Test
	public void BookRepositoryTest_FindById_ReturnBook() {


		Book book=Book.builder().ISBN("isbn").title("title").author("author").id(1L).build();

		Optional<Book> savedBook=bookRepository.findById(book.getId());


		Assertions.assertThat(savedBook).isNotNull();

	}


	@Test
	public void BookRepositoryTest_UpdateBook_ReturnBook() {


		Book book=Book.builder().ISBN("isbn").title("title").author("author").build();
		bookRepository.save(book);
		Book savedBook=bookRepository.getById(book.getId());

		savedBook.setAuthor("lolooo");
		bookRepository.save(savedBook);




		Assertions.assertThat(savedBook).isNotNull();

		Assertions.assertThat(savedBook.getAuthor()).isEqualTo("lolooo");

	}


	@Test
	public void BookRepositoryTest_ExistById_ReturnBoolean() {


		Book book=Book.builder().ISBN("isbn").title("title").author("author").id(1L).build();

		Boolean savedBook=bookRepository.existsByTitle(book.getTitle());


		Assertions.assertThat(savedBook).isNotNull();

	}




	/*

	@Test
	void itShouldCheckIfBookNameExist() {

		//given
		String title="it";
		Book book = new Book( "it","Amany","1234594321123");

		underTest.save(book);

		//when


		boolean expected=underTest.existsByTitle(title);

		//then
		assertThat(expected);


	}



	@Test
	void itShouldCheckIfBookNameDoesntExist() {

		//given
		String title="oihoiuh";


		//when


		boolean expected=(underTest.existsByTitle(title));

		//then
		assertThat(expected).isFalse();


	}*/

}
