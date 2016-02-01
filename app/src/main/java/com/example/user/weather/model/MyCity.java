package com.example.user.weather.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MyCity extends RealmObject {

    @PrimaryKey
    private int id;

    private String title;

    private String cityId;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCityId() {
        return cityId;
    }
}
