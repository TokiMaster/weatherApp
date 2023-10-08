package com.app.weather.controller;

import com.app.weather.dto.AverageTemperatureInfoDTO;
import com.app.weather.dto.CityInfoDTO;
import com.app.weather.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityInfoDTO> findAll() {
        return cityService.findAll();
    }

    @GetMapping("avg")
    public List<AverageTemperatureInfoDTO> averageTemperatureInfo(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "days") Integer days,
            @RequestParam(required = false) String sort) {
        return cityService.averageTemperatureInfo(name, days, sort);
    }

}
