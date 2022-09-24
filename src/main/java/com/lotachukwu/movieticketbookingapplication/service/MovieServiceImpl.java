package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.exception.MovieException;
import com.lotachukwu.movieticketbookingapplication.model.Movie;
import com.lotachukwu.movieticketbookingapplication.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public CreateMovieResponse createMovie(CreateMovieRequest createMovieRequest) {

        if(movieAlreadyExist(createMovieRequest.getTitle())){
            throw new MovieException("movie with title " + createMovieRequest.getTitle() + " already exists");
        }

        Movie movie = new Movie();
        movie.setCity(createMovieRequest.getCity());
        movie.setTitle(createMovieRequest.getTitle());
        movie.setDuration(createMovieRequest.getDuration());

        String [] genres = createMovieRequest.getGenre().toLowerCase().split(",");
        Set<String> movieGenres = Arrays.stream(genres).toList().stream().map(genre -> genre.trim()).collect(Collectors.toSet());
//        Set<String> movieGenres = new HashSet<>(genreSet);
        movie.setGenre(movieGenres);

        String [] languages = createMovieRequest.getLanguage().toLowerCase().split(",");
        Set<String> movieLanguages = Arrays.stream(languages).toList().stream().map(language -> language.trim()).collect(Collectors.toSet());
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

    @Override
    public
    List<Movie> searchMovie(String searchRequest) {

//        if(requestDoesNotExist(searchRequest)){
//
//        }
        List<Movie> movies = new ArrayList<>();
        List<Movie> lang = movieRepository.findMovieByLanguage(searchRequest);
        List<Movie> title = movieRepository.findMovieByTitle(searchRequest);
        List<Movie> genre = movieRepository.findMovieByGenre(searchRequest);

//        if(lang.size() > 0 || title.size() > 0 || genre.size() > 0){
//            movies.addAll(lang);
//            movies.addAll(title);
//            movies.addAll(genre);
//
//            log.info("{}",movies.size());
//        }
        if (lang.size() > 0 ){
            movies.addAll(lang);
            log.info("{}",movies.size());
        }

        if (genre.size() > 0){
            movies.addAll(genre);
            log.info("{}",movies.size());
        }
        if(title.size() > 0){
            movies.addAll(title);
        }
        log.info("{}",movies.size());
        log.info("movies======== {}", movies);
        return movies;
    }

    private boolean requestDoesNotExist(String searchRequest) {
        return !movieRepository.existsByTitle(searchRequest) || !movieRepository.existsByLanguage(searchRequest) || !movieRepository.existsByGenre(searchRequest) ;
//                || movieRepository.findMovieByGenre(searchRequest) || movieRepository.findMovieByTitle(searchRequest);
    }

    private boolean movieAlreadyExist(String title) {
        return movieRepository.existsByTitle(title);
    }
}
