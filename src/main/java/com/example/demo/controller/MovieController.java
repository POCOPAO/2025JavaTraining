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

import com.example.demo.service.MovieService;
import com.example.demo.DTO.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	// GET /api/books
	@GetMapping
	public ResponseEntity<List<MovieDTO>> getBooks(@RequestParam(required = false) String title,
			@RequestParam(required = false) String director, @RequestParam(required = false) Boolean isShowing) {
		List<MovieDTO> movies = null;

		// Normalize inputs: treat null/blank as "not provided"
		String varT = (title != null && !title.isBlank()) ? title.trim() : null;
		String varD = (director != null && !director.isBlank()) ? director.trim() : null;
		Boolean varS = isShowing; // can be null

		if (varT == null && varD == null && varS == null) {
			// No filter ? return all
			movies = movieService.getAllBooks();
		}
		if (varT != null && varD == null && varS == null) {
			// title only
			movies = movieService.searchByTitle(varT);
		}
		if (varT == null && varD != null && varS == null) {
			// director only
			movies = movieService.searchByDirector(varD);
		}
		if (varT == null && varD == null && varS != null) {
			// availability only
			movies = movieService.searchByIsShowing(varS);
		}
		if (varT == null && varD != null && varS != null) {
			// availability only
			movies = movieService.searchByDirectorAndIsShowing(varD, varS);
		}
		if (varT != null && varD != null && varS != null) {
			// availability only
			movies = movieService.searchByTitleDirectorAndIsShowing(varT, varD, varS);
		}
		return ResponseEntity.ok(movies);

	}

	@PostMapping("/addMovie")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MovieDTO> addMovies(@RequestParam(required = true) String title,
			@RequestParam(required = true) String director) {
		MovieDTO movie = null;
		movie = movieService.addMovie(title, director);
		return ResponseEntity.ok(movie);
	}

	@DeleteMapping("/delMovie")
	public ResponseEntity<String> delBook(@RequestParam String title, @RequestParam String director) {

		long count = movieService.deleteByTitleAndDirector(title, director);
		if (count == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie found with given title and director.");
		}
		return ResponseEntity.ok("Deleted " + count + " record(s).");
	}

}
