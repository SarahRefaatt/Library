package com.example.demo.controller;

import java.util.List;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	

	@PostMapping("/add")
	public void putBook(@RequestBody Book book)  throws ConstraintViolationException {

		
		bookService.addBook(book);
	}
	
	@GetMapping("/get_all")
	public List<Book> findBooks(){
		
		return bookService.getBooks();
	}
	
	
	@GetMapping("/get_id")
	public Book findBookById(@Param(value = "id") Long id) throws NoSuchElementException {
	
			return bookService.getBookById(id);
		
	}
	
	@PutMapping("/update")
	public Book update_Book(@RequestBody Book book) throws ConstraintViolationException {
		return bookService.updateBook(book);
	
		
	}
	
	@DeleteMapping("/delete")
	public void removeBook(@Param(value="id") Long id) {
		
		bookService.deleteBook(id);
	}

}
