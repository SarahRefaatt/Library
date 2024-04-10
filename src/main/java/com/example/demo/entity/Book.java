package com.example.demo.entity;

import java.time.Year;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Data

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@jakarta.validation.constraints.NotNull(message = "title must be written")
	String title;
	String author;
	Year publicationYear;
	String ISBN;
	
	

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Book( @jakarta.validation.constraints.NotNull(message = "title must be written") String title,
			String author,  String iSBN) {
		super();
		//this.id=id;
		this.title = title;
		this.author = author;
		ISBN = iSBN;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Year getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(Year publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	
	
	
}
