package app.worldplay.rappi.presenter.detalle;

import android.content.Context;

import app.worldplay.rappi.model.MoviesResponse;


public interface ContractMovie {

    interface ViewMovie{
        void getMovieInfo(String id, Context c);
        void setInfoMovie(
                MoviesResponse info);
        void setInfoMovieError(String txt);
        void setInfoMovieWarning(String txt);
    }

    interface PresentatorMovie{
        void getMovieInfo(String id, Context c);
        void getMovieInfoList(MoviesResponse im);
        void getInfoMovieError(String txt);
    }

    interface InteractorMovie{
        void getMovieInfo(String id, Context c);
    }
}