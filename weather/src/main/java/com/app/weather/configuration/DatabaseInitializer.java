package com.app.weather.configuration;

import com.app.weather.service.CityService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final CityService cityService;

    public DatabaseInitializer(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cityService.fetchAndSaveCityInfo();
    }

}
