package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
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
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByID(@PathParam("id") Long id) {
        MovieDTO movie = FACADE.getMovieByID(id);
          return GSON.toJson(movie, MovieDTO.class);
    }
    
    
    @Path("genre/{genre}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByGenre(@PathParam("genre") String genre) {
        List<MovieDTO> movies = new ArrayList<>();
        for (Movie m : FACADE.getByGenre(genre)){
            movies.add(new MovieDTO(m));
        }
        return GSON.toJson(movies);
    }
    
    @Path("title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByTitle(@PathParam("title") String title) {
        MovieDTO movie = FACADE.getByTitle(title);
        if (movie.getGenre() == null){
            return "{\"Title\":\"Not found\"}";
        }
        return GSON.toJson(movie);
    }
    
    /*
    @Path("oldest")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getOldestMovies() {
  List<MovieDTO> movie = (new MovieDTO(FACADE.getOldestMovies())).getList();
  
          return GSON.toJson(movie);
    }
    */
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<MovieDTO> movie = FACADE.getAll();
          return GSON.toJson(movie);
    }
    
    
    
    
}
