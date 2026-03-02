package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BookService;
import com.example.demo.DTO.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// GET /api/books
	@GetMapping
	public ResponseEntity<List<BookDTO>> getBooks(@RequestParam(required = false) String title,
			@RequestParam(required = false) String author, @RequestParam(required = false) Boolean isAvailable) {
		List<BookDTO> books = null;

		// Normalize inputs: treat null/blank as "not provided"
		String t = (title != null && !title.isBlank()) ? title.trim() : null;
		String a = (author != null && !author.isBlank()) ? author.trim() : null;
		Boolean avail = isAvailable; // can be null

		if (t == null && a == null && avail == null) {
			// No filter ? return all
			books = bookService.getAllBooks();
		}
		if (t != null && a == null && avail == null) {
			// title only
			books = bookService.searchByTitle(t);
		}
		if (t == null && a != null && avail == null) {
			// author only
			books = bookService.searchByAuthor(a);
		}
		if (t == null && a == null && avail != null) {
			// availability only
			books = bookService.searchByAvailability(avail);
		}
		if (t == null && a != null && avail != null) {
			// availability only
			books = bookService.searchByAuthorAndAvailability(a, avail);
		}
		if (t != null && a != null && avail != null) {
			// availability only
			books = bookService.searchByTitleAuthorAndAvailability(t, a, avail);
		}
		return ResponseEntity.ok(books);

	}

	@PostMapping("/addBook")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BookDTO> addBook(@RequestParam(required = true) String title,
			@RequestParam(required = true) String author) {
		BookDTO book = null;
		book = bookService.addBook(title, author);
		return ResponseEntity.ok(book);
	}

	@DeleteMapping("/delBook")
	public ResponseEntity<String> delBook(@RequestParam String title, @RequestParam String author) {

		long count = bookService.deleteByTitleAndAuthor(title, author);
		if (count == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found with given title and author.");
		}
		return ResponseEntity.ok("Deleted " + count + " record(s).");
	}

}
