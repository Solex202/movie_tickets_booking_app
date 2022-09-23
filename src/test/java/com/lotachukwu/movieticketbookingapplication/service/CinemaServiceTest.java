package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateCinemaRequest;
import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateCinemaResponse;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.exception.CinemaException;
import com.lotachukwu.movieticketbookingapplication.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.NestedTestConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class CinemaServiceTest {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private MovieService movieService;

    CreateMovieRequest createMovieRequest1;

    CreateMovieRequest createMovieRequest2;

    CreateMovieResponse movieResponse1;

    CreateMovieResponse movieResponse2;

    Movie movie2;
//    @Autowired
//    private MovieServiceImpl movieServiceImpl;

    @BeforeEach
    void setUp() {
        createMovieRequest1 = new CreateMovieRequest();
        createMovieRequest1.setTitle("title");
        createMovieRequest1.setLanguage("ibo, hausa, chinese");
        createMovieRequest1.setGenre("action, comedy");
        createMovieRequest1.setId("uy767ft767t87");

        createMovieRequest2 = new CreateMovieRequest();
        createMovieRequest2.setTitle("title 2");
        createMovieRequest2.setLanguage("ibo, hausa, chinese");
        createMovieRequest2.setGenre("action, comedy");
        createMovieRequest2.setId("kjhuytr65676");

        movieResponse1 = movieService.createMovie(createMovieRequest1);

        movieResponse2 = movieService.createMovie(createMovieRequest2);

    }

    @Test
    void testThatCanCreateCinema(){
        CreateCinemaRequest createCinemaRequest = new CreateCinemaRequest();
        createCinemaRequest.setCity("Yaba");
        createCinemaRequest.setName("film house");
        createCinemaRequest.setLocation("300 road");
//        createCinemaRequest.setMovies(List.of("movie 1, movie 2, movie 3"));
        createCinemaRequest.setNumberOfHalls(8);

        CreateCinemaResponse createCinema = cinemaService.createCinema(createCinemaRequest);

        assertThat(createCinema.getCity(), is(createCinemaRequest.getCity()));
        assertThat(createCinema.getName(), is(createCinemaRequest.getName()));
        assertThat(createCinema.getLocation(), is(createCinemaRequest.getLocation()));
        assertThat(createCinema.getNumberOfHalls(), is(createCinemaRequest.getNumberOfHalls()));
        assertThat(createCinema.getMovies(), is(createCinemaRequest.getMovies()));
    }

    @Test
    void testThatCinemaLocationIsUnique(){
        CreateCinemaRequest createCinemaRequest = new CreateCinemaRequest();
        createCinemaRequest.setCity("Yaba");
        createCinemaRequest.setName("genesis cinema");
        createCinemaRequest.setLocation("20 road");
        createCinemaRequest.setNumberOfHalls(6);

        CreateCinemaResponse createCinema = cinemaService.createCinema(createCinemaRequest);

        assertThat(createCinema.getCity(), is(createCinemaRequest.getCity()));
        assertThat(createCinema.getName(), is(createCinemaRequest.getName()));
        assertThat(createCinema.getLocation(), is(createCinemaRequest.getLocation()));
        assertThat(createCinema.getNumberOfHalls(), is(createCinemaRequest.getNumberOfHalls()));
        assertThat(createCinema.getMovies(), is(createCinemaRequest.getMovies()));

        CreateCinemaRequest createCinemaRequest2 = new CreateCinemaRequest();
        createCinemaRequest2.setCity("Aba");
        createCinemaRequest2.setName("ozone");
        createCinemaRequest2.setLocation("20 road");
        createCinemaRequest2.setNumberOfHalls(6);

        assertThrows(CinemaException.class, ()->cinemaService.createCinema(createCinemaRequest2));

    }


    @Test
    void testThatCanGetListOfCitiesWhereCinemasAreLocated(){

        CreateCinemaRequest createCinemaRequest = new CreateCinemaRequest();
        createCinemaRequest.setCity("Yaba");
        createCinemaRequest.setName("genesis cinema");
        createCinemaRequest.setLocation("25 road");
        createCinemaRequest.setNumberOfHalls(6);

        CreateCinemaResponse createCinema = cinemaService.createCinema(createCinemaRequest);

        assertThat(createCinema.getCity(), is(createCinemaRequest.getCity()));
        assertThat(createCinema.getName(), is(createCinemaRequest.getName()));
        assertThat(createCinema.getLocation(), is(createCinemaRequest.getLocation()));
        assertThat(createCinema.getNumberOfHalls(), is(createCinemaRequest.getNumberOfHalls()));
        assertThat(createCinema.getMovies(), is(createCinemaRequest.getMovies()));

        CreateCinemaRequest createCinemaRequest2 = new CreateCinemaRequest();
        createCinemaRequest2.setCity("Aba");
        createCinemaRequest2.setName("ozone");
        createCinemaRequest2.setLocation("30 road");
        createCinemaRequest2.setNumberOfHalls(6);

        CreateCinemaResponse createCinema2 =  cinemaService.createCinema(createCinemaRequest2);

        assertThat(createCinema2.getCity(), is(createCinemaRequest2.getCity()));
        assertThat(createCinema2.getName(), is(createCinemaRequest2.getName()));
        assertThat(createCinema2.getLocation(), is(createCinemaRequest2.getLocation()));
        assertThat(createCinema2.getNumberOfHalls(), is(createCinemaRequest2.getNumberOfHalls()));
        assertThat(createCinema2.getMovies(), is(createCinemaRequest2.getMovies()));

        assertThat(cinemaService.getListOfCinema().size(), is(2));

        List<String> cinemaCities = cinemaService.getListOfAllCinemaCities();

        assertThat(cinemaCities.size(), is(2));


    }

    @AfterEach
    void tearDown() {
        cinemaService.deleteAll();
    }
}