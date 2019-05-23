package app.worldplay.rappi.presenter.details;

import android.content.Context;

import app.worldplay.rappi.model.MoviesResponse;

public class MoviePresenter implements ContractMovie.PresentatorMovie{

    ContractMovie.ViewMovie viewMovie;
    ContractMovie.InteractorMovie interactorMovie;

    public MoviePresenter(ContractMovie.ViewMovie viewMovie) {
        this.viewMovie = viewMovie;
        interactorMovie = new MovieInteractor(this);
    }

    @Override
    public void getMovieInfo(String id, Context c) {
        interactorMovie.getMovieInfo(id, c);
    }

    @Override
    public void getMovieInfoList(MoviesResponse im) {
        viewMovie.setInfoMovie(im);
    }

    @Override
    public void getInfoMovieError(String txt) {
        viewMovie.setInfoMovieError(txt);
    }

}