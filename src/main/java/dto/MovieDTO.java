/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author Patrick
 */
public class MovieDTO {
    
     private int year;
    private String title;
    private String[] actors;
    
    public MovieDTO(Movie movie){
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.actors = movie.getActors();
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
    
    
}
