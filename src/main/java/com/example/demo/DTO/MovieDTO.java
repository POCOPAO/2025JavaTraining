package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;


public class MovieDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Director is required")
    private String director;

    private Boolean isShowing;

    public MovieDTO() {}

    public MovieDTO(String title, String director, Boolean isShowing) {
        this.title = title;
        this.director = director;
        this.isShowing = isShowing;
    }

    // --- Getters / Setters ---
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }


    public Boolean getIsShowing() { return isShowing; }
    public void setIsShowing(Boolean isShowing) { this.isShowing = isShowing; }
    
    
}

