package app.worldplay.rappi.service;

import java.util.List;

import app.worldplay.rappi.model.Genre;
import app.worldplay.rappi.model.Movie;
import app.worldplay.rappi.model.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDBService {

    @GET("/3/movie/{id}")
    Call<Movie> getMovie(@Path("id") int id);

    @GET("/3/movie/popular")
    Call<Movies> getPopularMovies();

    @GET("/3/movie/upcoming")
    Call<Movies> getUpcomingMovies();

    @GET("/3/movie/top_rated")
    Call<Movies> getTopRatedMovies();

    @GET("/3/genre/movie")
    Call<List<Genre>> getGeners();
}
