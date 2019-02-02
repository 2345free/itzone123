package com.itzone123.weather.data.server.service;

import com.itzone123.weather.data.server.vo.WeatherResponse;

public interface WeatherDataService {

    WeatherResponse getDataByCityId(String cityId);
}
