package com.example.user.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TempEntity extends EntityBase{

    private double day;

    private double eve;

    private double night;

    private double max;

    private double min;
}
