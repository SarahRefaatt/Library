package com.example.demo.controller;

import java.util.List;

import com.example.demo.exception.NoSuchElementException;
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

import com.example.demo.entity.Patron;
import com.example.demo.service.PatronService;

@RestController
@RequestMapping("/api/patron")
public class PatronController {
	
	@Autowired
	PatronService patronService;
	
	

	@PostMapping("/add")
	public void putPatron(@RequestBody Patron patron) throws ConstraintViolationException {

		
		patronService.addPatron(patron);
	}
	
	@GetMapping("/get_all")
	public List<Patron> findPatrons(){
		
		return patronService.getPatrons();
	}
	
	
	@GetMapping("/get_id")
	public Patron findPatronById(@Param(value = "id") Long id) throws NoSuchElementException {
		
		return patronService.getPatronById(id);
	}
	
	@PutMapping("/update")
	public Patron update_Patron(@RequestBody Patron patron) throws ConstraintViolationException{
		return patronService.updatePatron(patron);
	
		
	}
	
	@DeleteMapping("/delete")
	public void removePatron(@Param(value="id") Long id) {
		
		patronService.deletePatron(id);
	}

}
