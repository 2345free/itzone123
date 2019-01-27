package com.itzone123.weather.forecast.webapp.client;

import com.itzone123.weather.forecast.webapp.vo.City;
import com.itzone123.weather.forecast.webapp.vo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "micro-weather-gateway", path = "/api")
public interface DataClient {

    @GetMapping("/city-service/cities")
    List<City> listCities();

    @GetMapping("/weather-service/weather/cities/{cityId}")
    WeatherResponse getWeatherDataByCityId(@PathVariable("cityId") String cityId);
}
