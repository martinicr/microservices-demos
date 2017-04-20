package com.example.controller;


import com.example.model.Movie;
import com.example.persistence.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieController controller;

    @Test
    public void getAllMovies() throws Exception{

        Movie movie = mock(Movie.class);


        given(movieRepository.findAll()).willReturn(asList(movie));


        ResponseEntity<List<Movie>> response = controller.getAllMovies();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    public void getById() throws Exception{

        Movie movie = mock(Movie.class);

        ArgumentCaptor<Long> idArg = ArgumentCaptor.forClass(Long.class);

        given(movieRepository.findById(1L)).willReturn(movie);

        ResponseEntity<Movie> response = controller.getById(1L);

        verify(movieRepository, times(1)).findById(idArg.capture());

        assertThat(idArg.getValue()).isEqualTo(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void getById_whenIdNotFound_then404() throws Exception{

        given(movieRepository.findById(1L)).willReturn(null);

        ResponseEntity<Movie> response = controller.getById(1L);

        verify(movieRepository, times(1)).findById(anyLong());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }


}
