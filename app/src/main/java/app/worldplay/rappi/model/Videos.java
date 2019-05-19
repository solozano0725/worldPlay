
package app.worldplay.rappi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Videos implements Serializable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    private final static long serialVersionUID = -1775544742471563883L;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
