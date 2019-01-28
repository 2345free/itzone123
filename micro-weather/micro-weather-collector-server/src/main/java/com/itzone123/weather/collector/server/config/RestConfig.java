package com.itzone123.weather.collector.server.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest Configuration.
 */
@Configuration
public class RestConfig {

    private final RestTemplateBuilder builder;

    public RestConfig(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
}
