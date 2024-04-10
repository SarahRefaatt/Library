package com.example.demo;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	BookRepository underTest;
	
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();;
	}
	
	
	
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
		
		
	}

}
