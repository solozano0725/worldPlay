package app.worldplay.rappi.api.api;

import java.util.List;

import app.worldplay.rappi.api.api.model.Movies;

public interface OnGetMoviesCallback {
    void onSuccess(List<Movies> movies);
    void onError();
}
