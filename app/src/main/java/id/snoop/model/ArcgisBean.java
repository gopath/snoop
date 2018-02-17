package id.snoop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gopath on 2/7/2018.
 */

public class ArcgisBean extends BaseApiResult {

    @SerializedName("objectIdFieldName")
    public String objectIdFieldName;
    @SerializedName("globalIdFieldName")
    public String globalIdFieldName;
    @SerializedName("geometryType")
    public String geometryType;
    @SerializedName("spatialReference")
    public SpatialReference spatialReference;
    @SerializedName("fields")
    public List<Fields> fields;
    @SerializedName("features")
    public List<Features> features;

    public static class SpatialReference {
        @SerializedName("wkid")
        public int wkid;
        @SerializedName("latestWkid")
        public int latestWkid;
    }

    public static class Fields {
    }

    public static class Attributes {
    }

    public static class Geometry {
        @SerializedName("x")
        public double x;
        @SerializedName("y")
        public double y;
    }

    public static class Features {
        @SerializedName("attributes")
        public Attributes attributes;
        @SerializedName("geometry")
        public Geometry geometry;
    }
}
