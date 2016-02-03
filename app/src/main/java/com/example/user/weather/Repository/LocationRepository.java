package com.example.user.weather.Repository;

import com.example.user.weather.model.Location;

import java.util.List;

public interface LocationRepository {

    void save(Location location);

    void delete(Location location);

    List<Location> findAll();
}
