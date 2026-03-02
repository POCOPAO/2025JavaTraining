package com.example.demo.repository;

import com.example.demo.DAO.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByAvailable(Boolean available);
    List<Book> findByAuthorIgnoreCaseAndAvailable(String author, Boolean available);
    List<Book> findByTitleContainingIgnoreCaseAndAuthorIgnoreCaseAndAvailable(String title, String author, Boolean available);
    long deleteByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);
}