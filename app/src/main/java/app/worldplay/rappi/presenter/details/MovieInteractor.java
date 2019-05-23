package app.worldplay.rappi.presenter.details;

import android.content.Context;
import android.util.Log;

import app.worldplay.rappi.R;
import app.worldplay.rappi.model.MoviesResponse;
import app.worldplay.rappi.network.retrofit.RestApiAdapter;
import app.worldplay.rappi.presenter.home.HomeInteractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieInteractor implements ContractMovie.InteractorMovie {

    private final ContractMovie.PresentatorMovie presentatorMovie;

    public MovieInteractor(ContractMovie.PresentatorMovie presentatorMovie) {
        this.presentatorMovie = presentatorMovie;
    }

    @Override
    public void getMovieInfo(String id, final Context c) {
        Call<MoviesResponse> call = new RestApiAdapter(c).doConnection(3).getMoviesList(id);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    presentatorMovie.getMovieInfoList(response.body());
                } else {
                    presentatorMovie.getInfoMovieError(c.getString(R.string.check_internet));
                }
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                presentatorMovie.getInfoMovieError(c.getString(R.string.check_internet));
            }
        });
    }
}
