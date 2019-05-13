package app.worldplay.rappi.service;

import android.support.annotation.NonNull;

import app.worldplay.rappi.OnGetMoviesCallback;
import app.worldplay.rappi.model.Movies;
import app.worldplay.rappi.model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.worldplay.rappi.common.Constants.API_KEY;
import static app.worldplay.rappi.common.Constants.LANGUAGE;

public class MoviesServices {


    private TMDbService api;

    private MoviesServices(TMDbService api) {
        this.api = api;
    }

    public void getMoviesPopular(final OnGetMoviesCallback callback) {
        //api.getPopularMovies(API_KEY, LANGUAGE, 1)
        api.getPopularMovies()
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                        if (response.isSuccessful()) {
                            Movies moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getResults() != null) {
                                callback.onSuccess(moviesResponse.getResults());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

}
