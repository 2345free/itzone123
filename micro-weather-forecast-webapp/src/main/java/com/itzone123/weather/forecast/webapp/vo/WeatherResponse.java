package com.itzone123.weather.forecast.webapp.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Weather Response.
 */
@Data
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Weather data;
    private Integer status;
    private String desc;
}
