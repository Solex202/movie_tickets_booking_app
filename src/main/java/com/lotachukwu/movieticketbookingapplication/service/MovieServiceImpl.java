package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.exception.MovieException;
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

        if(movieAlreadyExist(createMovieRequest.getTitle())){
            throw new MovieException("movie with title" + createMovieRequest.getTitle() + "already exists");
        }

        Movie movie = new Movie();
        movie.setCity(createMovieRequest.getCity());
        movie.setTitle(createMovieRequest.getTitle());
        movie.setDuration(createMovieRequest.getDuration());

        String [] genres = createMovieRequest.getGenre().toLowerCase().split(",");
        Set<String> movieGenres = new HashSet<>(Arrays.asList(genres));
        movie.setGenre(movieGenres);

        String [] languages = createMovieRequest.getLanguage().toLowerCase().split(",");
        Set<String> movieLanguages = new HashSet<>(Arrays.asList(languages));
        movie.setLanguage(movieLanguages);

        Movie savedMovie = movieRepository.save(movie);

        CreateMovieResponse response = new CreateMovieResponse();
        Set<String> genreSet = savedMovie.getGenre();
        Set<String> languageSet = savedMovie.getLanguage();

        StringBuilder sbGenre = new StringBuilder();
        for (String genre : genreSet){
            sbGenre.append(genre).append(",");
        }
        StringBuilder sbLanguage = new StringBuilder();
        for (String language : languageSet){
            sbLanguage.append(language).append(",");
        }
        response.setLanguage(sbLanguage.toString());
        response.setGenre(sbGenre.toString());
        response.setCity(savedMovie.getCity());
        response.setTitle(savedMovie.getTitle());
        response.setDuration(savedMovie.getDuration());

        return response;
    }

    @Override
    public void deleteAll() {
        movieRepository.deleteAll();
    }

    private boolean movieAlreadyExist(String title) {
        return movieRepository.existsByTitle(title);
    }
}
