package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BorrowingRecordService;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {
	
	@Autowired
	BorrowingRecordService borrowingRecordService;
	
	@PostMapping("/book/patron")
	public void borrow_Book(@Param(value = "bookId") Long bookId,@Param(value = "patronId")Long patronId) {
	
		borrowingRecordService.borrowBook(bookId, patronId);
		
		
		
	}
	
	
	@PutMapping("/book/patron")
	public void return_Book(@Param(value = "bookId") Long bookId,@Param(value = "patronId")Long patronId){
		
		borrowingRecordService.returnBook(bookId, patronId);
		
		
		
		
	}
	
	
	
	

}
