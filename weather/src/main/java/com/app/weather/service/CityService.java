package com.app.weather.service;

import com.app.weather.model.City;
import com.app.weather.model.CityResponse;
import com.app.weather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CityService {

    @Value("${API.url}")
    private String url;

    @Value("${API.novi-sad}")
    private String noviSad;

    @Value("${API.malmo}")
    private String malmo;

    @Value("${API.rethymno}")
    private String rethymno;

    private final String[] cities = {noviSad, malmo, rethymno};

    private final RestTemplate restTemplate;

    private final CityRepository cityRepository;

    public CityService(RestTemplate restTemplate, CityRepository cityRepository) {
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
    }

    public final void fetchAndSaveCityInfo() {
        for (String city : cities) {
            ResponseEntity<CityResponse> cityResponse = restTemplate.getForEntity(url + city, CityResponse.class);
            City newCity = new City();
            newCity.setId(cityResponse.getBody().getCity().getId());
            newCity.setName(cityResponse.getBody().getCity().getName());
            newCity.setCountry(cityResponse.getBody().getCity().getCountry());
            newCity.setPopulation(cityResponse.getBody().getCity().getPopulation());
            cityRepository.save(newCity);
        }
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

}
