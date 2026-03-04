package com.example.demo.mapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.DAO.Movie;

import com.example.demo.DTO.MovieDTO;

@Component
public class MovieMapper {

	public MovieDTO toDto(Movie movie) {
		MovieDTO dto = new MovieDTO();
		dto.setTitle(movie.getTitle());
		dto.setDirector(movie.getDirector());
		dto.setIsShowing(movie.getIsShowing());
		return dto;
	}

	public Movie toEntity(MovieDTO movieDTO) {
		Movie movie = new Movie();
		movie.setTitle(movieDTO.getTitle());
		movie.setDirector(movieDTO.getDirector());
		movie.setIsShowing(movieDTO.getIsShowing());
		return movie;
	}
	
	public List<MovieDTO> toDtoList(List<Movie> movie) {
		return movie.stream().sorted(Comparator.comparing(Movie::getId)).map(this::toDto).collect(Collectors.toList());
	}
	
}
