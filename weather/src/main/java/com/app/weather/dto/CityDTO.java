package com.app.weather.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {

    private final String name;

    private final String country;

    private final int population;

    public CityDTO(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

}
