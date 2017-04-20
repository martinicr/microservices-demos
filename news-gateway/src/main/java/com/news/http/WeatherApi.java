package com.news.http;


import com.news.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {

    @GET("weather/{location}")
    Call<Weather> getWeatherForLocation(@Path("location") String location);

}
