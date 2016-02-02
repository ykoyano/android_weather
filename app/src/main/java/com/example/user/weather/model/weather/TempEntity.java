package com.example.user.weather.model.weather;

import com.example.user.weather.model.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TempEntity extends EntityBase {

    private double day;

    private double eve;

    private double night;

    private double max;

    private double min;
}
