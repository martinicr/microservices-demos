package com.news.config;

import com.news.http.LocalNewsApi;
import com.news.http.WeatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.List;

@Configuration
public class BeanConfig {

    @Autowired
    Environment env;

    @Autowired
    private LoadBalancerClient loadBalancer;

//    @Autowired
//    private DiscoveryClient discoveryClient;
    
    @Bean
    String weatherInstance(){
        String weatherServiceName = env.getProperty("weather.service.name", "weather-service");
        return this.loadBalancer.choose(weatherServiceName).getUri().toString();

    }

    @Bean
    String localNewsInstance(){
        String localNewsServiceName = env.getProperty("localnews.service.name", "local-news");
        return this.loadBalancer.choose(localNewsServiceName).getUri().toString();
    }

    @Bean
    WeatherApi weatherApi() {

        System.out.println(weatherInstance());

        return new Retrofit.Builder()
                .baseUrl(weatherInstance())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(WeatherApi.class);
    }

    @Bean
    LocalNewsApi localNewsApi(){

        System.out.println(localNewsInstance());

        return new Retrofit.Builder()
                .baseUrl(localNewsInstance())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(LocalNewsApi.class);
    }

}
