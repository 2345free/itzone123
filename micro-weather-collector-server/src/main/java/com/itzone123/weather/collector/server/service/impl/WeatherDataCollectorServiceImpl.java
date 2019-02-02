package com.itzone123.weather.collector.server.service.impl;

import com.itzone123.weather.collector.server.service.WeatherDataCollectorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WeatherDataCollectorServiceImpl implements WeatherDataCollectorService, InitializingBean {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private final RestTemplate restTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    @Value("${redis.persist.weather-data.timeout}")
    private long timeOut;

    public WeatherDataCollectorServiceImpl(RestTemplate restTemplate, StringRedisTemplate stringRedisTemplate) {
        this.restTemplate = restTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void collectorDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }
        if (StringUtils.isNotBlank(strBody)) {
            // 数据写入缓存
            ops.set(key, strBody, timeOut, TimeUnit.SECONDS);
        }

    }

    @Override
    public void afterPropertiesSet() {
        log.info("--------------redis data timeOut:{} --------------", timeOut);
    }
}
