package com.example.user.weather.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "YDF")
public class GeoCode extends EntityBase {

    public GeoCode() {
    }

    @Path("feature/gid/geometry/coordinates")
    @Element
    private List<String> geo;

    @Path("feature/gid/geometry/name")
    @Element
    private List<String> title;

    public List<String> getGeo() {
        return geo;
    }

    public List<String> getTitle() {
        return title;
    }


    public void setTitle(List<String> title) {
        this.title = title;
    }

    public void setGeo(List<String> geo) {
        this.geo = geo;
    }
}
