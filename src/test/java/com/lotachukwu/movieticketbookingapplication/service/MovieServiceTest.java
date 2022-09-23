package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.exception.MovieException;
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


    @Test
    void createMovie() throws Exception {

        CreateMovieRequest createMovieRequest = new CreateMovieRequest();
        createMovieRequest.setCity("lagos");
        createMovieRequest.setGenre("action, drama, thriller");
        createMovieRequest.setLanguage("english, dutch, french");
        createMovieRequest.setId("2321232");
        createMovieRequest.setTitle("last days at juno");
        createMovieRequest.setDuration("02:45:23");

        CreateMovieResponse createMovieResponse = movieService.createMovie(createMovieRequest);

        assertThat(createMovieResponse.getCity(), is(createMovieRequest.getCity()));
        assertThat(createMovieResponse.getTitle(), is(createMovieRequest.getTitle()));
        assertThat(createMovieResponse.getDuration(), is(createMovieRequest.getDuration()));
        assertThat(createMovieResponse.getLanguage().contains("english"), is(true));
        assertThat(createMovieResponse.getLanguage().contains("dutch"), is(true));
        assertThat(createMovieResponse.getLanguage().contains("french"), is(true));
        assertThat(createMovieResponse.getGenre().contains("action"), is(true));
        assertThat(createMovieResponse.getGenre().contains("drama"), is(true));
        assertThat(createMovieResponse.getGenre().contains("thriller"), is(true));

    }

    @Test
    void testThatCannotAdd_a_movieTwice() throws Exception {

        CreateMovieRequest createMovieRequest = new CreateMovieRequest();
        createMovieRequest.setCity("lagos");
        createMovieRequest.setGenre("action, drama, thriller");
        createMovieRequest.setLanguage("english, dutch, french");
        createMovieRequest.setId("2321232");
        createMovieRequest.setTitle("last days at juno yaba");
        createMovieRequest.setDuration("02:45:23");

        CreateMovieResponse createMovieResponse = movieService.createMovie(createMovieRequest);

        assertThat(createMovieResponse.getCity(), is(createMovieRequest.getCity()));
        assertThat(createMovieResponse.getTitle(), is(createMovieRequest.getTitle()));
        assertThat(createMovieResponse.getDuration(), is(createMovieRequest.getDuration()));
        assertThat(createMovieResponse.getLanguage().contains("english"), is(true));
        assertThat(createMovieResponse.getLanguage().contains("dutch"), is(true));
        assertThat(createMovieResponse.getLanguage().contains("french"), is(true));
        assertThat(createMovieResponse.getGenre().contains("action"), is(true));
        assertThat(createMovieResponse.getGenre().contains("drama"), is(true));
        assertThat(createMovieResponse.getGenre().contains("thriller"), is(true));


        CreateMovieRequest createMovieRequest2 = new CreateMovieRequest();
        createMovieRequest2.setCity("lagos");
        createMovieRequest2.setGenre("action, drama, thriller");
        createMovieRequest2.setLanguage("english, dutch, french");
        createMovieRequest2.setId("9876567");
        createMovieRequest2.setTitle("last days at juno yaba");
        createMovieRequest2.setDuration("02:45:23");

        assertThrows(MovieException.class,()->movieService.createMovie(createMovieRequest2));

    }
    @AfterEach
    void tearDown() {

        movieService.deleteAll();
    }
}
