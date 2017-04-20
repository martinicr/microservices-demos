package com.news.service;

import com.news.command.LocalNewsHystrixCommand;
import com.news.command.WeatherHystrixCommand;
import com.news.http.LocalNewsApi;
import com.news.http.WeatherApi;
import com.news.model.CityNews;
import com.news.model.LocalNews;
import com.news.model.Weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class NewsCompositor {

    private static final Logger LOG = LoggerFactory.getLogger(NewsCompositor.class);
    
    private LocalNewsApi localNewsApi;

    private WeatherApi weatherApi;

    @Autowired
    public NewsCompositor(LocalNewsApi localNewsApi, WeatherApi weatherApi){
        this.localNewsApi = localNewsApi;
        this.weatherApi = weatherApi;
    }

    public CityNews getNewsForCity(String city) throws Exception {

        Future<LocalNews> localNewsFuture = new LocalNewsHystrixCommand(localNewsApi, city).queue();

        Future<Weather> weatherFuture = new WeatherHystrixCommand(weatherApi, city).queue();

        LocalNews localNews = localNewsFuture.get();
        Weather weather = weatherFuture.get();

//        LocalNews localNews = new LocalNewsHystrixCommand(localNewsApi, city).execute();
//
//        Weather weather = new WeatherHystrixCommand(weatherApi, city).execute();

//        LocalNews localNews = getLocalNews(city);
//        Weather weather = getWeather(city);

        return new CityNews(city, weather, localNews);
    }
}
