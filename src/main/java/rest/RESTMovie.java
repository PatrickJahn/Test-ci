package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ActorsDTO;
import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class RESTMovie {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByID(@PathParam("id") Long id) {
        MovieDTO movie = FACADE.getMovieByID(id);
          return GSON.toJson(movie, MovieDTO.class);
    }
    @Path("oldest")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getOldestMovies() {
        List<Movie> movie = FACADE.getOldestMovies();
        
          return GSON.toJson(movie);
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Movie> movie = FACADE.getAll();
        
          return GSON.toJson(movie);
    }
    
    @Path("actors/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getActorsByTitle(@PathParam("title") String title) {
        ActorsDTO actors = FACADE.getActorsByTittle(title);
        
          return GSON.toJson(actors);
    }
}