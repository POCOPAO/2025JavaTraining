package com.example.demo.service;

import com.example.demo.DAO.Movie;
import com.example.demo.DTO.MovieDTO;
import com.example.demo.repository.MovieRepository;
import com.example.demo.mapper.MovieMapper;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

	private final MovieRepository repo;
	private final MovieMapper movieMapper;

	public MovieService(MovieRepository repo, MovieMapper movieMapper) {
		this.repo = repo;
		this.movieMapper = movieMapper;
	}

	public List<MovieDTO> getAllBooks() {
		return movieMapper.toDtoList(repo.findAll());
				}

	public List<MovieDTO> searchByTitle(String title) {
		return movieMapper.toDtoList(repo.findByTitleContainingIgnoreCase(title));
	}

	public List<MovieDTO> searchByDirector(String director) {
		return movieMapper.toDtoList(repo.findByDirectorIgnoreCase(director));				
	}

	public List<MovieDTO> searchByIsShowing(Boolean isShowing) {
		return movieMapper.toDtoList(repo.findByIsShowing(isShowing));
	}

	public List<MovieDTO> searchByDirectorAndIsShowing(String director, Boolean isShowing) {
		return movieMapper.toDtoList(repo.findByDirectorIgnoreCaseAndIsShowing(director, isShowing));				
	}

	public List<MovieDTO> searchByTitleDirectorAndIsShowing(String title, String director, Boolean isShowing) {
		return movieMapper.toDtoList(repo.findByTitleContainingIgnoreCaseAndDirectorIgnoreCaseAndIsShowing(title, director, isShowing));
				
	}

	public MovieDTO addMovie(String title, String director) {
		Movie newBook = new Movie(title, director, Boolean.TRUE);
		repo.save(newBook);
		return movieMapper.toDto(newBook);
	}

	@Transactional
	public long deleteByTitleAndDirector(String title, String director) {
		return repo.deleteByTitleIgnoreCaseAndDirectorIgnoreCase(title, director);
	}

}
