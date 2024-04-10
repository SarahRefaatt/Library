package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Book;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.repository.BookRepository;

public class BookService {

	@Autowired
	BookRepository bookRepository;

	

	public BookService(BookRepository bookRepository2) {
		// TODO Auto-generated constructor stub
	}

	public void addBook(Book book) {
		
		bookRepository.save(book);
	}

	public List<Book> getBooks() {
		
		return bookRepository.findAll();
	}

	public Book getBookById(Long id) throws NoSuchElementException {
		
		if(bookRepository.findById(id)!=null)
			return bookRepository.findById(id).orElseThrow();
		else {
			throw new NoSuchElementException("no user with id"+id);
		}
	}

	public Book updateBook(Book book) {
		
		Book current=bookRepository.getById(book.getId());
		
		current.setId(book.getId());
		current.setAuthor(book.getAuthor());
		current.setPublicationYear(book.getPublicationYear());
		current.setTitle(book.getTitle());
		
		current.setISBN(book.getISBN());
		
		bookRepository.save(current);
		
		return current;
		
	}

	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

}