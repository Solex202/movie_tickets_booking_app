package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createMovie() throws Exception {

        CreateMovieRequest createMovieRequest = new CreateMovieRequest();
        createMovieRequest.setCity("lagos");
        createMovieRequest.setGenre("Action, drama, thriller");
        createMovieRequest.setLanguage("english, dutch, french");
        createMovieRequest.setId("2321232");
        createMovieRequest.setTitle("last days at juno");

        CreateMovieResponse createMovieResponse = movieService.createMovie(createMovieRequest);

        assertThat(createMovieResponse.getCity(), is(createMovieRequest.getCity()));
        assertThat(createMovieResponse.getGenre(), is(createMovieRequest.getGenre()));
        assertThat(createMovieResponse.getTitle(), is(createMovieRequest.getTitle()));
        assertThat(createMovieResponse.getLanguage(), is(createMovieRequest.getLanguage()));


    }
}