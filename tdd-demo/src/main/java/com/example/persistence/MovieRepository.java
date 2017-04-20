package com.example.persistence;


import com.example.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    private static Map<Long, Movie> MOVIE_DATA = new HashMap<Long, Movie>(){{
        put(1L, new Movie("star wars", "space stuff!!", new Date()));
        put(2L, new Movie("the matrix", "science fiction", new Date()));
        put(3L, new Movie("the notebook", "chick flick", new Date()));
    }};


    public Movie findById(Long id){
        return MOVIE_DATA.get(id);
    }

    public List<Movie> findAll(){
        return MOVIE_DATA.values().stream().collect(Collectors.toList());
    }

    public Movie findByName(String name){

        return MOVIE_DATA.values().stream()
                         .filter(Objects::nonNull)
                         .filter(e -> e.getTitle().equals(name))
                         .findFirst().get();
    }



}
