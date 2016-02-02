package com.example.user.weather.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class GeoEntity extends EntityBase {

    @Expose
    private String city;
}