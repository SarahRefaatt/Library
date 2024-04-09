package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Patron;
import com.example.demo.repository.PatronRepository;

@Service
public class PatronService {

	@Autowired
	PatronRepository patronRepository;
	
	
	
	public void addPatron(Patron patron) {
		
		patronRepository.save(patron);
	}
	
	
	public List<Patron> getPatrons(){
		
		return patronRepository.findAll();
	}
	
	
	public Patron getPatronById(Long id) {
		
		return patronRepository.findById(id).orElseThrow();
	}
	
	public Patron updatePatron(Patron patron) {
		
		Patron current=patronRepository.findById(patron.getId()).orElseThrow();
		
		current.setId(patron.getId());
		current.setName(patron.getName());
		current.setContactInfo(patron.getContactInfo());
		current.setBorrowedBooks(patron.getBorrowedBooks());
		
		patronRepository.save(current);
		
		return current;
		
	}
	
	public void deletePatron(Long id) {
		patronRepository.deleteById(id);
	}
	
	
	
	
}
