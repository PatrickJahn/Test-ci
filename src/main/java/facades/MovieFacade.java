package facades;

import dto.ActorsDTO;
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
            TypedQuery<Movie> tq = em.createQuery("SELECT m FROM Movie m WHERE m.year = (SELECT min(m.year) FROM Movie m)", Movie.class);
            return tq.getResultList();
        }finally{  
            em.close();
        }
        
    }
     
     public List<Movie> getAll(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> tq = em.createQuery("SELECT m FROM Movie m", Movie.class);
            return tq.getResultList();
        }finally{  
            em.close();
        }
        
    }

     
      public String[] getActorsByTittle(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<String[]> tq = em.createQuery("SELECT m.actors FROM Movie m WHERE m.title = ?1", String[].class);
            tq.setParameter(1, name);
         
            return tq.getSingleResult();
        }finally{  
            em.close();
        }
        
    }
}
