package com.itzone123.weather.forecast.webapp.service;

import com.itzone123.weather.forecast.webapp.vo.Weather;

/**
 * Weather Report Service.
 */
public interface WeatherForecastService {

    Weather getWeatherDataByCityId(String cityId);
}
