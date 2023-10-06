package com.app.weather.mapper;

import com.app.weather.dto.CityInfoDTO;
import com.app.weather.model.City;

public class CityMapper {

    public static CityInfoDTO toDTO(City city) {
        return new CityInfoDTO(city.getName(), city.getCountry(), city.getPopulation(), city.getTemperature(), city.getTime());
    }

}
