package app.worldplay.rappi.api.api;

import app.worldplay.rappi.api.api.model.GenresResponse;
import app.worldplay.rappi.api.api.model.Movies;
import app.worldplay.rappi.api.api.model.MoviesResponse;
import app.worldplay.rappi.api.api.model.ReviewsResponse;
import app.worldplay.rappi.api.api.model.TrailersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbService {
    @GET("movie/popular")
    Call<MoviesResponse> getPopular(@Query("api_key") String apiKey,
                                    @Query("language") String language,
                                    @Query("page") int page);
    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("search/movie")
    Call<MoviesResponse> getSearchMovies(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("page") int page,
                                         @Query("query") String searchQuery);
    @GET("movie/{movie_id}")
    Call<Movies> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("movie/{movie_id}/videos")
    Call<TrailersResponse> getTrailers(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/reviews")
    Call<ReviewsResponse> getReviews(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("trending/{media_type}/{time_window}")
    Call<MoviesResponse> getTrendingMovies(
            @Path("media_type") String media_type,
            @Path("time_window") String time_window,
            @Query("api_key") String apiKey);
}
