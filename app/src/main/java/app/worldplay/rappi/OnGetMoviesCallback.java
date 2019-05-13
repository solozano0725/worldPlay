package app.worldplay.rappi;

import java.util.List;

import app.worldplay.rappi.model.Result;

public interface OnGetMoviesCallback {

    void onSuccess(List<Result> movies);

    void onError();
}
