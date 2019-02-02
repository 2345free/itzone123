package com.itzone123.weather.service.impl;

import com.itzone123.weather.client.CityClient;
import com.itzone123.weather.service.CityService;
import com.itzone123.weather.vo.CityCollection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityClient cityClient;

    public CityServiceImpl(@Qualifier("cityClient") CityClient cityClient) {
        this.cityClient = cityClient;
    }

    @Override
    public CityCollection listCities() {
        return cityClient.listCities();
    }
}
