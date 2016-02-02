package com.example.user.weather.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GeoEntity extends EntityBase {

    @Expose
    private String city;

    @Expose
    private String town;

    @Expose
    private double x;

    @Expose
    private double y;
}