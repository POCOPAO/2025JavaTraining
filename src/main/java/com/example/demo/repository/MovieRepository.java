package com.example.demo.repository;

import com.example.demo.DAO.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByDirectorIgnoreCase(String director);
    List<Movie> findByIsShowing(Boolean isShowing);
    List<Movie> findByDirectorIgnoreCaseAndIsShowing(String director, Boolean isShowing);
    List<Movie> findByTitleContainingIgnoreCaseAndDirectorIgnoreCaseAndIsShowing(String title, String director, Boolean isShowing);
    long deleteByTitleIgnoreCaseAndDirectorIgnoreCase(String title, String director);
}