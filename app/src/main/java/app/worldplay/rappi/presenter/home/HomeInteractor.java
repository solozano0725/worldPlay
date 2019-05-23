package app.worldplay.rappi.presenter.home;

import android.content.Context;
import android.util.Log;

import app.worldplay.rappi.R;
import app.worldplay.rappi.model.MoviesList;
import app.worldplay.rappi.network.retrofit.RestApiAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.worldplay.rappi.BuildConfig.KEYAPI;
import static app.worldplay.rappi.BuildConfig.LENG;

public class HomeInteractor implements ContractHome.Interactor {

    ContractHome.Presentator presentator;

    public HomeInteractor(ContractHome.Presentator presentator) {
        this.presentator = presentator;
    }

    @Override
    public void getMovies(final String type, int page, final Context c) {
        Call<MoviesList> call = new RestApiAdapter(c).doConnection(3).getMovieListGeneral(type, KEYAPI,LENG, page);
        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {

                if (response.isSuccessful()) {
                    presentator.getMoviesList(response.body());
                } else {
                    presentator.getMoviesError(c.getString(R.string.error_msg));
                }
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                presentator.getMoviesError(c.getString(R.string.check_internet));
            }
        });
    }

    @Override
    public void getMoviesFilter(String txt, final Context c) {
        Call<MoviesList> call = new RestApiAdapter(c).doConnection(3).getSearchMovies(KEYAPI, LENG, txt, 1);
        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (response.isSuccessful()) {
                    presentator.getMoviesFilterList(response.body());
                } else {
                    presentator.getMoviesError(c.getString(R.string.error_msg));
                }
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                presentator.getMoviesError(c.getString(R.string.check_internet));
            }
        });
    }




}