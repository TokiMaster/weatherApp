package com.app.weather.service;

import com.app.weather.dto.CityInfoDTO;
import com.app.weather.mapper.CityMapper;
import com.app.weather.model.City;
import com.app.weather.dto.CityApiResponseDTO;
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

    @Value("${API.cities}")
    private String[] cities;

    private final RestTemplate restTemplate;

    private final CityRepository cityRepository;

    public CityService(RestTemplate restTemplate, CityRepository cityRepository) {
        this.restTemplate = restTemplate;
        this.cityRepository = cityRepository;
    }

    public final void fetchAndSaveCityInfo() {
        for (String city : cities) {
            ResponseEntity<CityApiResponseDTO> cityResponse = restTemplate.getForEntity(url + city, CityApiResponseDTO.class);
            City newCity = new City();
            newCity.setId(cityResponse.getBody().getCity().getId());
            newCity.setName(cityResponse.getBody().getCity().getName());
            newCity.setCountry(cityResponse.getBody().getCity().getCountry());
            newCity.setPopulation(cityResponse.getBody().getCity().getPopulation());
            newCity.setTemperature(cityResponse.getBody().getList().get(0).getTemperatureInfo().getTemp());
            newCity.setTime(cityResponse.getBody().getList().get(0).getTime());
            cityRepository.save(newCity);
        }
    }

    public List<CityInfoDTO> findAll() {
        return cityRepository.findAll()
                .stream()
                .map(CityMapper::toDTO)
                .toList();
    }

}
