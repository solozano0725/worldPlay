package app.worldplay.rappi.api.api;

import java.util.List;

import app.worldplay.rappi.api.api.model.Trailers;

public interface OnGetTrailersCallback {
    void onSuccess(List<Trailers> trailers);
    void onError();
}
