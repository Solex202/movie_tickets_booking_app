package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.dto.response.SearchMovieResponse;
import com.lotachukwu.movieticketbookingapplication.exception.MovieException;
import com.lotachukwu.movieticketbookingapplication.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void testThatCanSearchMovieByTitle(){
        CreateMovieRequest createMovieRequest = new CreateMovieRequest();
        createMovieRequest.setCity("lagos");
        createMovieRequest.setGenre("action, drama, thriller");
        createMovieRequest.setLanguage("english, dutch, french");
        createMovieRequest.setId("2321232");
        createMovieRequest.setTitle("last days");
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
        createMovieRequest2.setId("2321232");
        createMovieRequest2.setTitle("shege");
        createMovieRequest2.setDuration("02:45:23");

        CreateMovieResponse createMovieResponse2 = movieService.createMovie(createMovieRequest2);

        assertThat(createMovieResponse2.getCity(), is(createMovieRequest2.getCity()));
        assertThat(createMovieResponse2.getTitle(), is(createMovieRequest2.getTitle()));
        assertThat(createMovieResponse2.getDuration(), is(createMovieRequest2.getDuration()));
        assertThat(createMovieResponse2.getLanguage().contains("english"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("dutch"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("french"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("action"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("drama"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("thriller"), is(true));


        List<Movie> seenTitle = movieService.searchMovie(createMovieRequest2.getTitle());
        assertThat(seenTitle.size(), is(1));
    }

    @Test
    void testThatCanSearchMovieByLanguage(){
        CreateMovieRequest createMovieRequest = new CreateMovieRequest();
        createMovieRequest.setCity("lagos");
        createMovieRequest.setGenre("action, drama, thriller");
        createMovieRequest.setLanguage("english, dutch, french");
        createMovieRequest.setId("2321232");
        createMovieRequest.setTitle("last days");
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
        createMovieRequest2.setId("2321232");
        createMovieRequest2.setTitle("shege");
        createMovieRequest2.setDuration("02:45:23");

        CreateMovieResponse createMovieResponse2 = movieService.createMovie(createMovieRequest2);

        assertThat(createMovieResponse2.getCity(), is(createMovieRequest2.getCity()));
        assertThat(createMovieResponse2.getTitle(), is(createMovieRequest2.getTitle()));
        assertThat(createMovieResponse2.getDuration(), is(createMovieRequest2.getDuration()));
        assertThat(createMovieResponse2.getLanguage().contains("english"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("dutch"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("french"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("action"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("drama"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("thriller"), is(true));


        List<Movie> movieWithLang = movieService.searchMovie("french");
        assertEquals(2, movieWithLang.size());
    }

    @Test
    void testThatCanSearchMovieByGenre(){
        CreateMovieRequest createMovieRequest = new CreateMovieRequest();
        createMovieRequest.setCity("lagos");
        createMovieRequest.setGenre("action, drama, thriller");
        createMovieRequest.setLanguage("english, dutch, french");
        createMovieRequest.setId("2321232");
        createMovieRequest.setTitle("last days");
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
        createMovieRequest2.setId("2321232");
        createMovieRequest2.setTitle("shege");
        createMovieRequest2.setDuration("02:45:23");

        CreateMovieResponse createMovieResponse2 = movieService.createMovie(createMovieRequest2);

        assertThat(createMovieResponse2.getCity(), is(createMovieRequest2.getCity()));
        assertThat(createMovieResponse2.getTitle(), is(createMovieRequest2.getTitle()));
        assertThat(createMovieResponse2.getDuration(), is(createMovieRequest2.getDuration()));
        assertThat(createMovieResponse2.getLanguage().contains("english"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("dutch"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("french"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("action"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("drama"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("thriller"), is(true));


        List<Movie> movieWithGenre = movieService.searchMovie("thriller");
        assertEquals(2, movieWithGenre.size());
    }


    @Test
    void testThatCanSearchMovieByGenre22222222(){
        CreateMovieRequest createMovieRequest = new CreateMovieRequest();
        createMovieRequest.setCity("lagos");
        createMovieRequest.setGenre("action, drama, thriller");
        createMovieRequest.setLanguage("english, dutch, french");
        createMovieRequest.setId("2321232");
        createMovieRequest.setTitle("last days");
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
        createMovieRequest2.setId("2321232");
        createMovieRequest2.setTitle("shege");
        createMovieRequest2.setDuration("02:45:23");

        CreateMovieResponse createMovieResponse2 = movieService.createMovie(createMovieRequest2);

        assertThat(createMovieResponse2.getCity(), is(createMovieRequest2.getCity()));
        assertThat(createMovieResponse2.getTitle(), is(createMovieRequest2.getTitle()));
        assertThat(createMovieResponse2.getDuration(), is(createMovieRequest2.getDuration()));
        assertThat(createMovieResponse2.getLanguage().contains("english"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("dutch"), is(true));
        assertThat(createMovieResponse2.getLanguage().contains("french"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("action"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("drama"), is(true));
        assertThat(createMovieResponse2.getGenre().contains("thriller"), is(true));


        SearchMovieResponse movieWithGenre = movieService.searchMovie2(1,10,"thriller");
//        assertEquals(2, movieWithGenre.size());
    }

    @Test
    void testThatWillThrowExceptionIfSearchIsUnsuccessful(){
        assertThrows(MovieException.class, ()->movieService.searchMovie("dem mama"));
    }
    @AfterEach
    void tearDown() {
        movieService.deleteAll();
    }

}
