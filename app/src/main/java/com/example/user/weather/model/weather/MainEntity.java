package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import com.google.gson.annotations.Expose;

public class MainEntity extends EntityBase {

    @Expose
    private double temp;

    @Expose
    private double tempMax;

    @Expose
    private double tempMin;

    public double getTemp() {
        return temp;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
