package com.example.demo.controller;

import com.example.demo.exception.DuplicateException;
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
	public String borrow_Book(@Param(value = "bookId") Long bookId,@Param(value = "patronId")Long patronId)   {

		try {
			borrowingRecordService.borrowBook(bookId, patronId);
			return "DONE";

		}catch (Exception ex){
			return "Error this book is already borrowed";
		}

	}
	
	
	@PutMapping("/book/patron")
	public void return_Book(@Param(value = "bookId") Long bookId,@Param(value = "patronId")Long patronId){
		
		borrowingRecordService.returnBook(bookId, patronId);
		
	}
	
	
	
	

}
