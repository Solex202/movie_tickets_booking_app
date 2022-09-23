package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.model.Movie;
import com.lotachukwu.movieticketbookingapplication.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;
    @Override
    public CreateMovieResponse createMovie(CreateMovieRequest createMovieRequest) {

        Movie movie = new Movie();
        movie.setCity(createMovieRequest.getCity());
        movie.setTitle(createMovieRequest.getTitle());
        String [] genres = createMovieRequest.getGenre().toLowerCase().split(",");

        Set <String> movieGenres = new HashSet<>(Arrays.asList(genres));
        movie.setGenre(movieGenres);

        String [] languages = createMovieRequest.getLanguage().toLowerCase().split(",");
        Set<String> movieLanguages = new HashSet<>(Arrays.asList(languages));
        movie.setLanguage(movieLanguages);

        Movie savedMovie = movieRepository.save(movie);

        CreateMovieResponse response = new CreateMovieResponse();
        response.setCity(savedMovie.getCity());
        response.setGenre(savedMovie.getGenre().toString());
        response.setLanguage(String.valueOf(savedMovie.getLanguage()));
        response.setTitle(savedMovie.getTitle());

        return response;
    }
}
