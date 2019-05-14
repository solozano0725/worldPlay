package app.worldplay.rappi.api.api;

import java.util.List;

import app.worldplay.rappi.api.api.model.Genres;

public interface OnGetGenresCallback {
    void onSuccess(List<Genres> genres);
    void onError();
}
