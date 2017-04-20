package com.weather.config;

import com.weather.service.OpenWeatherMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class BeanConfig {

    private static final Logger LOG = LoggerFactory.getLogger(BeanConfig.class);

    @Autowired
    Environment env;

    @Bean
    @Qualifier("openweathermapKey")
    String openweathermapKey(){
        String key = env.getRequiredProperty("openweathermap.api.key");
        LOG.debug("Using openweathermap api key {}", key);
        return key;
    }
    
    @Bean
    OpenWeatherMap openWeatherMap(){

        return new Retrofit.Builder()
                .baseUrl(env.getRequiredProperty("openweathermap.api.url"))
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(OpenWeatherMap.class);
    }


}
