package app.worldplay.rappi.api.api;

import app.worldplay.rappi.api.api.model.Movies;

public interface OnGetMovieCallback {
    void onSuccess(Movies movie);
    void onError();
}
