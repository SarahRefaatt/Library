package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowingRecord;
import com.example.demo.entity.Patron;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.PatronRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowingRecordService {

	
	@Autowired
	BorrowingRecordRepository borrowingRecordRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	PatronRepository patronRepository;
	
	@Transactional
	public void borrowBook(Long bookId,Long patronId) {
		
		  List<Book> books = new ArrayList<>();

			
		  Book book=bookRepository.findById(bookId).orElseThrow();
			
	      Patron patron=patronRepository.findById(patronId).orElseThrow();

		  BorrowingRecord current = new BorrowingRecord();
		  
		  current.setBookId(bookId);
		  current.setPatronId(patronId);
		  borrowingRecordRepository.save(current);
		  
		  if (book!=null && patron!=null) {
			  
			  //books.add(book);//remove !!
			  //books.remove(book);
			  patron.getBorrowedBooks().add(book);
			  patronRepository.save(patron);
			  //bookRepository.delete(book);


		  }

	}
	
	@Transactional

	public void returnBook(Long bookId, Long patronId) {
	    Patron patron = patronRepository.findById(patronId).orElseThrow();

	    if (patron != null) {
	        List<Book> borrowedBooks = patron.getBorrowedBooks();
	        for (int i = 0; i < borrowedBooks.size(); i++) {
	            Book book = borrowedBooks.get(i);
	            if (book != null && book.getId() != null && book.getId().equals(bookId)) {
	                //borrowedBooks.remove(i);
					patron.getBorrowedBooks().remove(i);

	                patronRepository.save(patron);
	                break;
	            }
	        }
	    }

	    List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findALLByBookId(bookId);
	    if (!borrowingRecords.isEmpty()) {
	        borrowingRecordRepository.deleteAll(borrowingRecords);
	    }

	    
	    
	    //BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookId(bookId);
	    //borrowingRecordRepository.deleteById(borrowingRecord.getId());
	}
	
	
	
	/*public void returnBook(Long bookId, Long patronId) {
	    Patron patron = patronRepository.findById(patronId).orElseThrow();

	    if (patron != null) {
	        Iterator<Book> iterator = patron.getBorrowedBooks().iterator();
	        while (iterator.hasNext()) {
	            Book book = iterator.next();
	            if (book.getId().equals(bookId)) {
	                iterator.remove();
	                break;
	            }
	        }
	        patronRepository.save(patron);
	    }

	    BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookId(bookId);
	    borrowingRecordRepository.deleteById(borrowingRecord.getId());
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
