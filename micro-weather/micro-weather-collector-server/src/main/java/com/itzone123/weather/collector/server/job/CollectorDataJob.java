package com.itzone123.weather.collector.server.job;

import com.itzone123.weather.collector.server.client.CityClient;
import com.itzone123.weather.collector.server.service.WeatherDataCollectorService;
import com.itzone123.weather.collector.server.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CollectorDataJob implements InitializingBean {

    private final CityClient cityClient;
    private final WeatherDataCollectorService collectorService;
    @Value("${quartz.job.weather-collector.corn}")
    private String expr;

    public CollectorDataJob(@Qualifier("cityClient") CityClient cityClient, WeatherDataCollectorService collectorService) {
        this.cityClient = cityClient;
        this.collectorService = collectorService;
    }

    @Scheduled(cron = "${quartz.job.weather-collector.corn}")
    public void executeCollector() {
        log.info("Collector Weather Data Job. Start！");
        List<City> cities = cityClient.listCities();
        if (CollectionUtils.isNotEmpty(cities)) {
            cities.forEach(city -> {
                log.info("Weather Data Sync Job, cityId:" + city.getCityId());
                collectorService.collectorDataByCityId(city.getCityId());
            });
        }
        log.info("Collector Weather Data Job. End！");
    }

    @Override
    public void afterPropertiesSet() {
        log.info("--------------collectorDataJob's cron:{} --------------", expr);
    }
}
