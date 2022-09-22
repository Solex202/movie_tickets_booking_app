package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateCinemaRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateCinemaResponse;
import com.lotachukwu.movieticketbookingapplication.exception.CinemaException;
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
    private MovieServiceImpl movieServiceImpl;

    @BeforeEach
    void setUp() {
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

    @AfterEach
    void tearDown() {
    }
}