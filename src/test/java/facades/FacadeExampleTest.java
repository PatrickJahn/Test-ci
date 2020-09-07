package facades;

import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            String[] act = {"Pia k", "Jack Sparrow", "David Hasselhoff"};
            em.persist(new Movie(1982,"Bob the builder", act));
            em.persist(new Movie(1995, "Bob the builder 2", act));
            em.persist(new Movie(2001, "Bob the builder 21", act));       
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(3, facade.getMovieCount(), "Expects two rows in the database");
    }
    
    @Test 
    @Disabled
    public void testGetById(){
        Long i = (long) 16;
       MovieDTO d = facade.getMovieByID(i);
        assertEquals(1995, d.getYear());
        
    }
    
    
    @Test
    public void testGetOldestMovies(){
     
       List<Movie> oldestM = facade.getOldestMovies();
       assertEquals((int) 1982, oldestM.get(0).getYear());
 
        
    }
    
      @Test
    public void testGetAll(){
     
       List<Movie> all = facade.getAll();
       
       assertEquals(3, all.size());
        
    }

    @Test
    public void testGetActorsByTittle(){
     
       String[] actual = facade.getActorsByTittle("Bob the builder");
  
       String[] ecp = {"Pia k", "Jack Sparrow", "David Hasselhoff"};
       
          for (int i = 0; i < ecp.length; i++) {
              assertEquals(ecp[i], actual[i]);
    }
        
    }
    
}
