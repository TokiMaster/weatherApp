package com.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureInfoHolderDTO {

    @JsonProperty("main")
    private TemperatureInfoDTO temperatureInfo;

    @JsonProperty("dt_txt")
    private String time;

    public TemperatureInfoDTO getTemperatureInfo() {
        return temperatureInfo;
    }

    public String getTime() {
        return time;
    }

}
