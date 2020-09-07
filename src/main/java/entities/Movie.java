package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String title;
    private String genre;
  //  private String[] actors;
    

    public Movie() {
    }
            

    public Movie(int year, String title, String genre) {
        this.year = year;
        this.title = title;
        this.genre = genre;
      //  this.actors = actors;
       
    }

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    
    
    
    
   /* public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
*/
    
    
    
    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", year=" + year + ", title=" + title + '}';
    }

   
   
    

   
}
