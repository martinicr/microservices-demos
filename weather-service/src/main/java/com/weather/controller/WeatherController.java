package com.weather.controller;

import com.weather.model.Weather;
import com.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.weather.model.WeatherResponse.fromWeather;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> weather(@PathVariable("location") String location){

        Optional<Weather> localWeather = weatherService.getWeather(location);
        if(localWeather.isPresent()){

            return ResponseEntity.ok(fromWeather(localWeather.get()));
        }

        return ResponseEntity.notFound().build();

//        return new HashMap<String, String>(){{
//            put("pressure", String.valueOf(weatherService.getWeather(location).getMain().getPressure()));
//        }};

//        return ImmutableMap.of("presure",
//                String.valueOf(this.weatherService.getWeather(location).getMain().getPressure()));

    }


}
