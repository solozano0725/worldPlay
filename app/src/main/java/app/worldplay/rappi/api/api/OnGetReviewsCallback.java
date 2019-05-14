package app.worldplay.rappi.api.api;

import java.util.List;

import app.worldplay.rappi.api.api.model.Reviews;

public interface OnGetReviewsCallback {
    void onSuccess(List<Reviews> reviews);
    void onError();
}
