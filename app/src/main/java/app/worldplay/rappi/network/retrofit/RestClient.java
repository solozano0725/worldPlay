package app.worldplay.rappi.network.retrofit;

import app.worldplay.rappi.model.MoviesList;
import app.worldplay.rappi.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static app.worldplay.rappi.BuildConfig.KEYAPI;
import static app.worldplay.rappi.BuildConfig.LANG;

public interface RestClient {

    @GET("search/movie")
    Call<MoviesList> getSearchMovies(@Query("api_key") String key, @Query("language") String lang, @Query("query") String val, @Query("page") int page);

    @GET("movie/{id}")
    Call<MoviesList> getMovieListGeneral(@Path("id") String taskId, @Query("api_key") String key, @Query("language") String lang, @Query("page") int page);

    @GET("movie/{id}?api_key=" + KEYAPI + "&append_to_response=videos" + LANG)
    Call<MoviesResponse> getMoviesList(@Path("id") String taskId);
}