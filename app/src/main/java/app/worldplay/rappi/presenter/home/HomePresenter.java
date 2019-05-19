package app.worldplay.rappi.presenter.home;

import android.content.Context;

import app.worldplay.rappi.model.MoviesList;

public class HomePresenter implements ContractHome.Presentator {

    ContractHome.View view;
    ContractHome.Interactor interactor;

    public HomePresenter(ContractHome.View view) {
        this.view = view;
        this.interactor = new HomeInteractor(this);
    }

    @Override
    public void showLoading() {
        view.showLoading();
    }

    @Override
    public void closeLoading() {
        view.closeLoading();
    }

    @Override
    public void getMovies(String type, int page, Context context) {
        showLoading();
        interactor.getMovies(type, page, context);
    }

    @Override
    public void getMoviesList(MoviesList movies) {
        view.getMoviesList(movies);
        closeLoading();
    }

    @Override
    public void getMoviesFilter(String txt, Context context) {
        interactor.getMoviesFilter(txt,context);
    }

    @Override
    public void getMoviesFilterList(MoviesList peliculas) {
        view.getMoviesFilterList(peliculas);
    }

    @Override
    public void getMoviesError(String txt) {
        view.getMoviesError(txt);
    }

}
