package com.example.user.weather.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiCityEntity extends EntityBase {

    @Expose
    private CoordEntity coord;
}
