package com.app.weather.service;

import com.app.weather.dto.AverageTemperatureInfoDTO;
import com.app.weather.dto.CityDTO;
import com.app.weather.dto.CityInfoDTO;
import com.app.weather.dto.TemperatureInfoDTO;
import com.app.weather.exception.BusinessLogicException;
import com.app.weather.mapper.CityMapper;
import com.app.weather.model.City;
import com.app.weather.dto.CityApiResponseDTO;
import com.app.weather.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<AverageTemperatureInfoDTO> averageTemperatureInfo(String name, Integer days) {
        if (days == null || (days < 1 || days > 5)){
            throw new BusinessLogicException("Number of days must be between 1 and 5");
        }
        ArrayList<AverageTemperatureInfoDTO> avgTemp = new ArrayList<>();
        if (name == null) {
            for (String city : cities) {
                calculateAverageTemperature(days, avgTemp, city);
            }
        }else if (!Arrays.stream(cities).toList().contains(name)) {
            throw new BusinessLogicException(String.format("Citi %s does not exist!", name));
        } else {
            calculateAverageTemperature(days, avgTemp, name);
        }
        return avgTemp;
    }

    private void calculateAverageTemperature(Integer days, ArrayList<AverageTemperatureInfoDTO> avgTemp, String city) {
        ResponseEntity<CityApiResponseDTO> cityResponse;
        cityResponse = restTemplate.getForEntity(url + city + "&cnt=" + days * 8, CityApiResponseDTO.class);
        double averageTemperature = cityResponse.getBody().getList()
                .stream()
                .mapToDouble(t -> t.getTemperatureInfo().getTemp())
                .average()
                .orElseThrow(() -> new BusinessLogicException("Failed to calculate average temperature"));

        AverageTemperatureInfoDTO temperatureInfo = new AverageTemperatureInfoDTO();
        CityDTO cityDTO = new CityDTO(cityResponse.getBody().getCity().getName(),
                cityResponse.getBody().getCity().getCountry(), cityResponse.getBody().getCity().getPopulation());
        temperatureInfo.setCity(cityDTO);
        temperatureInfo.setAverageTemperature(averageTemperature);
        temperatureInfo.setNumberOfDays(days);
        avgTemp.add(temperatureInfo);
    }

}
