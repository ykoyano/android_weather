package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainEntity extends EntityBase {

    @Expose
    private double tempMax;

    @Expose
    private double tempMin;
}