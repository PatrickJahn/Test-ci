package facades;


import dto.MovieDTO;
import entities.Movie;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
   public MovieDTO getMovieByID(Long id){
       EntityManager em = emf.createEntityManager();
         try{
            Movie movie = em.find(Movie.class, id);

            return new MovieDTO(movie);
            
        }finally{  
            em.close();
        }
   }
    
    

    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
     public List<Movie> getOldestMovies(){
        EntityManager em = emf.createEntityManager();
        try{
            // Kan ikke bruge DTO her, muligvis fordi jeg har query inde i en anden
            TypedQuery<Movie> tq = em.createQuery("SELECT m FROM Movie m WHERE m.year = (SELECT MIN(a.year) FROM Movie a)", Movie.class);
            return tq.getResultList();
        }finally{  
            em.close();
        }
        
    }
     
     public List<MovieDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<MovieDTO> tq = em.createQuery("SELECT m FROM Movie m", MovieDTO.class);
            return tq.getResultList();
        }finally{  
            em.close();
        }
        
    }
     
      public List<Movie> getByGenre(String genre){
        EntityManager em = emf.createEntityManager();
        try{
            // Kan ikke få lov til at bruge min DTO her..
            TypedQuery<Movie> tq = em.createQuery("SELECT m FROM Movie m WHERE m.genre = ?1", Movie.class);
           tq.setParameter(1, genre);
            return tq.getResultList();
        }finally{  
            em.close();
        }
        
    }
      
      
      public MovieDTO getByTitle(String titel){
        EntityManager em = emf.createEntityManager();
        try{
            // Kan ikke få lov til at bruge min DTO her..
            TypedQuery<Movie> tq = em.createQuery("SELECT m FROM Movie m WHERE m.title = ?1", Movie.class);
           tq.setParameter(1, titel);
            return new MovieDTO(tq.getSingleResult());
        }catch (Exception e) {
                    return new MovieDTO();
                    
        }finally{  
            em.close();
        }
        
    }

     
      
}
