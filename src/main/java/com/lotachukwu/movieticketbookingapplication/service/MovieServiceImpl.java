package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateMovieResponse;
import com.lotachukwu.movieticketbookingapplication.dto.response.SearchMovieResponse;
import com.lotachukwu.movieticketbookingapplication.exception.MovieException;
import com.lotachukwu.movieticketbookingapplication.model.Movie;
import com.lotachukwu.movieticketbookingapplication.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        movie.setAddedDate(LocalDateTime.now().toString());

        String [] genres = createMovieRequest.getGenre().toLowerCase().split(",");
        log.info("genres ======> {}", (Object) genres);
        Set<String> movieGenres = Arrays.stream(genres).toList().stream().map(genre -> genre.trim()).collect(Collectors.toSet());
        log.info("movie genre ====> {}", movieGenres);
        movie.setGenre(movieGenres);

        String [] languages = createMovieRequest.getLanguage().toLowerCase().split(",");
        log.info("languages ======> {}" , (Object) languages);
        Set<String> movieLanguages = Arrays.stream(languages).toList().stream().map(language -> language.trim()).collect(Collectors.toSet());
        log.info("movie language ======> {}", movieLanguages);
        movie.setLanguage(movieLanguages);

        Movie savedMovie = movieRepository.save(movie);

        CreateMovieResponse response = new CreateMovieResponse();
        Set<String> genreSet = savedMovie.getGenre();
        log.info("GENRESET {}", genreSet);
        Set<String> languageSet = savedMovie.getLanguage();
        log.info("LANGUAGESET {}", languageSet);

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

        log.info("response=======>{}", response);

        return response;
    }

    @Override
    public void deleteAll() {
        movieRepository.deleteAll();
    }

    @Override
    public List<Movie> searchMovie(String searchRequest) {
        return null;
    }

    @Override
    public SearchMovieResponse searchMovie2(int pageNumber, int pageSize, String searchRequest) {

        SearchMovieResponse response = new SearchMovieResponse();
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "addedTime");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(order));


        List<Movie> movies = new ArrayList<>();
        List<Movie> lang = movieRepository.findMovieByLanguage(searchRequest, pageable);
        log.info("LANGUAGE {}", lang);
        List<Movie> title = movieRepository.findMovieByTitle(searchRequest, pageable);
        log.info("TITLE {}", title);
        List<Movie> genre = movieRepository.findMovieByGenre(searchRequest, pageable);
        log.info("GENRE {}", genre);

        if(lang.size() > 0 || title.size() > 0 || genre.size() > 0){
            movies.addAll(lang);
            movies.addAll(title);
            movies.addAll(genre);

        }
        response.setMovies(movies);
        response.setCurrentPage(pageNumber);
        response.setPageSize(pageSize);
        response.setNoOfMovies(movies.size());

        if (movies.size() > 0){
        return response;
        }else throw new MovieException("No Movie found");
    }

//    @Override
//    public
//    List<Movie> searchMovie(String searchRequest) {
//
//        List<Movie> movies = new ArrayList<>();
//        List<Movie> lang = movieRepository.findMovieByLanguage(searchRequest);
//        List<Movie> title = movieRepository.findMovieByTitle(searchRequest);
//        List<Movie> genre = movieRepository.findMovieByGenre(searchRequest);
//
//        if(lang.size() > 0 || title.size() > 0 || genre.size() > 0){
//            movies.addAll(lang);
//            movies.addAll(title);
//            movies.addAll(genre);
//
//        }
//
//        if (movies.size() >0){
//        return movies;
//        }else throw new MovieException("Movie not found");
//    }


    private boolean movieAlreadyExist(String title) {
        return movieRepository.existsByTitle(title);
    }
}
