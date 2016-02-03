package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class MainEntity extends EntityBase {

    @Expose
    private double tempMax;

    @Expose
    private double tempMin;

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
