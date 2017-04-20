package com.weather.service;

import com.weather.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.Optional;

@Service
public class WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherService.class);

    private String openWeatherAppId;

    private OpenWeatherMap openWeatherMap;

    @Autowired
    public WeatherService(@Qualifier("openweathermapKey") String openWeatherAppId,
                          OpenWeatherMap openWeatherMap){

        this.openWeatherAppId = openWeatherAppId;
        this.openWeatherMap = openWeatherMap;
    }

    public Optional<Weather> getWeather(String location){

        LOG.debug("Retrieving weather data for {}", location);
        try {
            Call<Weather> call = openWeatherMap.getWeather(location, openWeatherAppId);
            return Optional.ofNullable(call.execute().body());
        } catch (IOException e){
            LOG.error("Can't make request to {}", location, e);
        }
        return Optional.empty();
    }

}
