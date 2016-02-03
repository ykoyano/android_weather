package com.example.user.weather.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeoEntity extends EntityBase {

    private int id;

    @Expose
    private String city;

    @Expose
    private String town;

    @Expose
    private double x;

    @Expose
    private double y;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getTown() {
        return town;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setCity(String city) {
        this.city = city;
    }
}