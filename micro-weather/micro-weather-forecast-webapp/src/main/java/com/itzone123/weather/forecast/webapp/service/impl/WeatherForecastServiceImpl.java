package com.itzone123.weather.forecast.webapp.service.impl;

import com.itzone123.weather.forecast.webapp.client.DataClient;
import com.itzone123.weather.forecast.webapp.service.WeatherForecastService;
import com.itzone123.weather.forecast.webapp.vo.Weather;
import com.itzone123.weather.forecast.webapp.vo.WeatherResponse;
import org.springframework.stereotype.Service;

/**
 * Weather Report Service.
 */
@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    private final DataClient dataClient;

    public WeatherForecastServiceImpl(DataClient dataClient) {
        this.dataClient = dataClient;
    }

    @Override
    public Weather getWeatherDataByCityId(String cityId) {

        // 由天气数据API微服务来提供
        WeatherResponse resp = dataClient.getWeatherDataByCityId(cityId);
        Weather data = null;
        if (resp != null) {
            data = resp.getData();
        }
        return data;
    }

}
