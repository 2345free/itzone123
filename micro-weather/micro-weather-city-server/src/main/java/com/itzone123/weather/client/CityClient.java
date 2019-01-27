package com.itzone123.weather.client;

import com.itzone123.weather.vo.CityCollection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "city-service",
        qualifier = "cityClient",
        fallback = CityClient.CityFallback.class,
        configuration = CityClient.Config.class)
public interface CityClient {

    @GetMapping(value = "/data/citylist.xml", headers = "User-Agent=Mozilla/5.0")
    CityCollection listCities();

    class CityFallback implements CityClient {

        @Override
        public CityCollection listCities() {
            return new CityCollection();
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
