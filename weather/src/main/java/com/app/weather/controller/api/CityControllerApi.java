package com.app.weather.controller.api;

import com.app.weather.dto.AverageTemperatureInfoDTO;
import com.app.weather.dto.CityInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface CityControllerApi {

    @Operation(summary = "Get average temperature information of predefined cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average temperature information retrieved successfully.",
                    content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AverageTemperatureInfoDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "City not found."),
    })
    List<AverageTemperatureInfoDTO> averageTemperatureInfo(
            @Parameter(description = "Name of the city (Novi Sad, Rethymno, Malmo). Optional") String name,
            @Parameter(description = "Number of days for which to retrieve average temperature (1 -5). Required") Integer days,
            @Parameter(description = "Sort order asc or desc (optional)") String sort);


    @Operation(summary = "Find all available cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cities found",
                    content = { @Content(mediaType = "application/json")})
    })
    List<CityInfoDTO> findAll();
}
