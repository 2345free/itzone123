package com.itzone123.weather.collector.server.client;

import com.itzone123.weather.collector.server.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "micro-weather-city-server",
        qualifier = "cityClient",
        fallback = CityClient.CityFallback.class,
        configuration = CityClient.Config.class)
public interface CityClient {

    @GetMapping(value = "/cities")
    List<City> listCities();

    class CityFallback implements CityClient {

        @Override
        public List<City> listCities() {
            return new ArrayList<>();
        }
    }

    @Configuration
    class Config {
        @Bean
        public CityFallback cityFallback() {
            return new CityFallback();
        }
    }
}
