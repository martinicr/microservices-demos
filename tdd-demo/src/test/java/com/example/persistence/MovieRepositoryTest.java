package com.example.persistence;


import com.example.model.Movie;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieRepositoryTest {

    MovieRepository movieRepository;

    @Before
    public void setUp() throws Exception{
        this.movieRepository = new MovieRepository();
    }

    @Test
    public void findById() throws Exception{

//        MovieRepository repository = new MovieRepository();

        Movie movie = movieRepository.findById(2L);

        assertThat(movie).isNotNull();
        assertThat(movie.getTitle()).isEqualTo("the matrix");
    }

    @Test
    public void findByName() throws Exception{

//        MovieRepository repository = new MovieRepository();

        Movie movie = movieRepository.findByName("star wars");

        assertThat(movie).isNotNull();
        assertThat(movie.getTitle()).isEqualTo("star wars");
    }

    @Test
    public void findAll() throws Exception{

//        MovieRepository repository = new MovieRepository();

        List<Movie> movies = movieRepository.findAll();

        assertThat(movies).isNotEmpty();
        assertThat(movies.size()).isEqualTo(3);
    }


}
