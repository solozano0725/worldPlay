
package app.worldplay.rappi.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCompany implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("logo_path")
    @Expose
    private String logoPath;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin_country")
    @Expose
    private String originCountry;
    private final static long serialVersionUID = -3605919673147538811L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

}