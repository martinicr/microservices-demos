package com.weather.service;


import com.weather.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMap {

    @GET("/data/2.5/weather")
    Call<Weather> getWeather(@Query("q") String location, @Query("appId") String appId);
    


}
