package app.worldplay.rappi.presenter.home;

import android.content.Context;

import app.worldplay.rappi.model.MoviesList;

public interface ContractHome {

    interface View{
        void showLoading();
        void closeLoading();

        void getMovies(String type, int page, Context context);
        void getMoviesList(MoviesList movies);

        void getMoviesFilter(String txt, Context c);
        void getMoviesFilterList(MoviesList movies);

        void getMoviesError(String txt);
    }

    interface Presentator{
        void showLoading();
        void closeLoading();

        void getMovies(String type, int page, Context c);
        void getMoviesList(MoviesList movies);


        void getMoviesFilter(String type, Context c);
        void getMoviesFilterList(MoviesList movies);

        void getMoviesError(String txt);
    }

    interface Interactor{
        void getMovies(String type, int page, Context c);
        void getMoviesFilter(String txt, Context c);

    }
}