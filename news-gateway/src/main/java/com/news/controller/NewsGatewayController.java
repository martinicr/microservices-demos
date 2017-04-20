package com.news.controller;

import com.news.model.CityNews;
import com.news.service.NewsCompositor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsGatewayController {

    private static final Logger LOG = LoggerFactory.getLogger(NewsGatewayController.class);

    private NewsCompositor newsCompositor;

    @Autowired
    public NewsGatewayController(NewsCompositor newsCompositor){
        this.newsCompositor = newsCompositor;
    }

    @GetMapping(value = "/news/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newsForCity(@PathVariable("city") String city){

        LOG.debug("==> News for {} <==", city);

        //TODO: validate city

        try {
            CityNews cityNews = this.newsCompositor.getNewsForCity(city);
            return ResponseEntity.ok(cityNews);
        } catch (Exception e){
            LOG.error("Error while retrieving city news for {}", city, e);
            return ResponseEntity.noContent().build();
        }
    }


}
