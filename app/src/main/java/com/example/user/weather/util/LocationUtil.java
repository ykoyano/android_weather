package com.example.user.weather.util;

import com.example.user.weather.model.Location;
import rx.Observable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class LocationUtil {

    public static ArrayList<String> toStringArray(List<Location> locations) {
        List<String> cities = Observable.from(locations).map(Location::getCity).toList().toBlocking().single();
        LinkedHashSet<String> set = new LinkedHashSet<>(cities);
        return new ArrayList<>(set);
    }
}
