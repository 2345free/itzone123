package com.itzone123.weather.forecast.webapp.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 天气信息.
 */
@Data
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String aqi;
    private String ganmao;
    private String wendu;
    private Yeaterday yesterday;
    private List<Forecast> forecast;
}
