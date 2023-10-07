package com.app.weather.dto;

public class AverageTemperatureInfoDTO  {

    private CityDTO city;

    private double averageTemperature;

    private int numberOfDays;

    public AverageTemperatureInfoDTO() {
    }

    public AverageTemperatureInfoDTO(CityDTO city, double averageTemperature, int numberOfDays) {
        this.city = city;
        this.averageTemperature = averageTemperature;
        this.numberOfDays = numberOfDays;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

}
