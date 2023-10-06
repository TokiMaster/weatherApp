package com.app.weather.dto;

import java.io.Serializable;

public class CityInfoDTO implements Serializable {

    private final String name;

    private final String country;

    private final int population;

    private final double temperature;

    private final String time;

    public CityInfoDTO(String name, String country, int population, double temperature, String time) {
        this.name = name;
        this.country = country;
        this.population = population;
        this.temperature = temperature;
        this.time = time;
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

    public double getTemperature() {
        return temperature;
    }

    public String getTime() {
        return time;
    }

}
