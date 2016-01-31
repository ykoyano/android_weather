package com.example.user.weather.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CityEntity extends EntityBase {

    private String title;

    private String id;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
