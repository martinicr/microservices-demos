package com.example.controller;

import com.example.model.Movie;
import com.example.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAllMovies(){

        List<Movie> movies = this.movieRepository.findAll();
        return ResponseEntity.ok(movies);

    }

    @GetMapping(value = "/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getById(@PathVariable("id") Long id){

        Movie movie = this.movieRepository.findById(id);
        if(null != movie) {
            return ResponseEntity.ok(movie);
        }

        return ResponseEntity.notFound().build();

    }

}
