package facades;


import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

      Movie m1 = new Movie(1982,"Bob the builder", "Fantasy");
    
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
          
            em.persist(m1);
            em.persist(new Movie(1995, "Bob the builder 2", "Thriller"));
            em.persist(new Movie(2001, "Bob the builder 21", "Sci-fi"));       
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
    public void testGetById(){
       Long i = m1.getId();
       MovieDTO d = facade.getMovieByID(i);
        assertEquals(1982, d.getYear());
        
    }
    
    /*
    @Test
    public void testGetOldestMovies(){
     
      // List<Movie> oldestM = facade.getOldestMovies();
       assertEquals((int) 1982, oldestM.get(0).getYear());
 
        
    }
*/
    
     @Test
    public void testGetbyGenre(){
        String genre = "Thriller";
          String title = "Bob the builder 2";
       List<Movie> mov = facade.getByGenre(genre);
       assertEquals(title, mov.get(0).getTitle());
 
        
    }
    
     @Test
    public void testGetByTittle(){
        String genre = "Thriller";
          String title = "Bob the builder 2";
       MovieDTO movie = facade.getByTitle(title);
       assertEquals(genre, movie.getGenre());
 
        
    }
    
      @Test
    public void testGetAll(){
     
       List<MovieDTO> all = facade.getAll();
       
       assertEquals(3, all.size());
        
    }

   
    
}
