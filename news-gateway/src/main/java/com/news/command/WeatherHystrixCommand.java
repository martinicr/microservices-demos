package com.news.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.news.http.WeatherApi;
import com.news.model.Weather;

public class WeatherHystrixCommand extends HystrixCommand<Weather> {

    private WeatherApi weatherApi;
    private String city;

    public WeatherHystrixCommand(WeatherApi weatherApi, String city) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Weather"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withExecutionTimeoutInMilliseconds(5000)));
        this.weatherApi = weatherApi;
        this.city = city;
    }

    @Override
    protected Weather run() throws Exception {
        return weatherApi.getWeatherForLocation(city).execute().body();
    }

    @Override
    protected Weather getFallback() {
        Weather weather = new Weather();

        weather.setCity(city);
        weather.setDescription("Weather currently unavailable for this location.");
        weather.setConditions("N/A");
        weather.setTemperature("- -");
        weather.setPressure("- -");
        weather.setHumidity("- -");

        return weather;
    }
}