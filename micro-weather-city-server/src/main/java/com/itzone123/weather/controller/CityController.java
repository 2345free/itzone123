package com.itzone123.weather.controller;

import com.itzone123.weather.service.CityService;
import com.itzone123.weather.vo.City;
import com.itzone123.weather.vo.CityCollection;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<City>> listCities() {
        List<City> cities = null;
        CityCollection cityCollection = cityService.listCities();
        if (cityCollection != null) {
            cities = cityCollection.getCities();
        }
        return ResponseEntity.ok(cities);
    }
}
