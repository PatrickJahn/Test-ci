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
public class ActorsDTO {
    
    String[] actors; 
    
    public ActorsDTO(Movie m){
        this.actors = m.getActors();
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
    
    
    
}
