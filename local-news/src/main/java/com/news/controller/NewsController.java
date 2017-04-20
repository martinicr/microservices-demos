package com.news.controller;


import com.news.model.LocalNews;
import com.news.persistence.News;
import com.news.persistence.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class NewsController {

    private static final Logger LOG = LoggerFactory.getLogger(NewsController.class);

    private NewsRepository newsRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @GetMapping(value = "/news/local/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newsForCity(@PathVariable("city") String city){

        LOG.debug("==> Local News for {} <==", city);

        Optional<News> dbNews = Optional.ofNullable(this.newsRepository.findByCity(city));
        if(dbNews.isPresent()) {
            return ResponseEntity.ok(
                    dbNews.map(n -> new LocalNews(n.getHeadline(), n.getNews())).get());
        }

        return ResponseEntity.notFound().build();
    }



}
