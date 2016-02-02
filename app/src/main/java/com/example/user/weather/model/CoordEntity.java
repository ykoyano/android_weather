package com.example.user.weather.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CoordEntity extends EntityBase {

    @Expose
    private double lat;

    @Expose
    private double lon;
}
