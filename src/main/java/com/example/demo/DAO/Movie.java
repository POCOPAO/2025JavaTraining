package com.example.demo.DAO;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "director", nullable = false, length = 50)
    private String director;

    @Column(name = "is_showing", nullable = false)
    private Boolean isShowing;

    /** JPA requires a no-arg constructor */
    public Movie() {}

    public Movie(String title, String director, Boolean isShowing) {
        this.title = title;
        this.director = director;
        this.isShowing = isShowing;
    }

    // --- Getters / Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    /** Business-friendly naming */
    public Boolean getIsShowing() { return isShowing; }
    public void setIsShowing(Boolean isShowing) { this.isShowing = isShowing; }

}
