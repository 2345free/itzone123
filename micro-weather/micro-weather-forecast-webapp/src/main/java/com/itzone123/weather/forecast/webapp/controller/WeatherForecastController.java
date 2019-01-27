package com.itzone123.weather.forecast.webapp.controller;

import com.itzone123.weather.forecast.webapp.client.DataClient;
import com.itzone123.weather.forecast.webapp.service.WeatherForecastService;
import com.itzone123.weather.forecast.webapp.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Weather Report Controller.
 */
@Slf4j
@RestController
@RequestMapping("/forecast")
public class WeatherForecastController {

    private final DataClient dataClient;
    private final WeatherForecastService weatherForecastService;

    public WeatherForecastController(WeatherForecastService weatherForecastService, DataClient dataClient) {
        this.weatherForecastService = weatherForecastService;
        this.dataClient = dataClient;
    }

    @GetMapping("/cities/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) {
        // 获取城市ID列表
        List<City> cityList = null;
        try {
            // 由城市数据API微服务提供数据
            cityList = dataClient.listCities();
        } catch (Exception e) {
            log.error("获取城市列表异常", e);
        }

        model.addAttribute("title", "广东省天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityList);
        model.addAttribute("forecast", weatherForecastService.getWeatherDataByCityId(cityId));
        return new ModelAndView("weather/forecast", "forecastModel", model);
    }
}
