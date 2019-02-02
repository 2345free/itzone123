package com.itzone123.weather.data.server.controller;

import com.itzone123.weather.data.server.service.WeatherDataService;
import com.itzone123.weather.data.server.vo.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Weather Controller.
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherDataService weatherDataService;

    public WeatherController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/cities/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) {
        return weatherDataService.getDataByCityId(cityId);
    }
}
