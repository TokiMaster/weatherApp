package com.app.weather.dto;

import com.app.weather.model.City;

import java.util.ArrayList;

public class CityApiResponseDTO {

    private City city;

    private ArrayList<TemperatureInfoHolderDTO> list;

    public City getCity() {
        return city;
    }

    public ArrayList<TemperatureInfoHolderDTO> getList() {
        return list;
    }
}
