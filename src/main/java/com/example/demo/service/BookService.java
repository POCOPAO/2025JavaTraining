package com.example.demo.service;

import com.example.demo.DAO.Book;
import com.example.demo.DTO.BookDTO;
import com.example.demo.repository.BookRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

	private final BookRepository repo;

	public BookService(BookRepository repo) {
		this.repo = repo;
	}

	public List<BookDTO> getAllBooks() {
		return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public List<BookDTO> searchByTitle(String title) {
		return repo.findByTitleContainingIgnoreCase(title).stream().map(this::toDto).collect(Collectors.toList());
	}

	public List<BookDTO> searchByAuthor(String author) {
		return repo.findByAuthorIgnoreCase(author).stream().map(this::toDto).collect(Collectors.toList());
	}

	public List<BookDTO> searchByAvailability(Boolean isAvailable) {
		return repo.findByAvailable(isAvailable).stream().map(this::toDto).collect(Collectors.toList());
	}

	public List<BookDTO> searchByAuthorAndAvailability(String author, Boolean isAvailable) {
		return repo.findByAuthorIgnoreCaseAndAvailable(author, isAvailable).stream().map(this::toDto)
				.collect(Collectors.toList());
	}

	public List<BookDTO> searchByTitleAuthorAndAvailability(String title, String author, Boolean isAvailable) {
		return repo.findByTitleContainingIgnoreCaseAndAuthorIgnoreCaseAndAvailable(title, author, isAvailable).stream()
				.map(this::toDto).collect(Collectors.toList());
	}

	public BookDTO addBook(String title, String author) {
		Book newBook = new Book(title, author, Boolean.TRUE);
		repo.save(newBook);
		return toDto(newBook);
	}

	@Transactional
	public long deleteByTitleAndAuthor(String title, String author) {
		return repo.deleteByTitleIgnoreCaseAndAuthorIgnoreCase(title, author);
	}

	private BookDTO toDto(Book b) {
		BookDTO dto = new BookDTO();
		dto.setTitle(b.getTitle());
		dto.setAuthor(b.getAuthor());
		dto.setIsAvailable(b.getAvailable());
		return dto;
	}

	private Book toEntity(BookDTO b) {
		Book book = new Book();
		book.setTitle(b.getTitle());
		book.setAuthor(b.getAuthor());
		book.setAvailable(b.getIsAvailable());
		return book;
	}
}
