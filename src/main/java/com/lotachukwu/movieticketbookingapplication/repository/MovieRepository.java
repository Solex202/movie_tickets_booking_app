package com.lotachukwu.movieticketbookingapplication.repository;

import com.lotachukwu.movieticketbookingapplication.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    boolean existsByTitle(String title);

//    List<Movie> findMovieByLanguage(String language);
//
//    List<Movie> findMovieByGenre(String genre);
//
//    List<Movie> findMovieByTitle(String title);

    boolean existsByLanguage(String searchRequest);

    boolean existsByGenre(String searchRequest);

    List<Movie> findMovieByLanguage(String searchRequest, Pageable pageable);

    List<Movie> findMovieByTitle(String searchRequest, Pageable pageable);

    List<Movie> findMovieByGenre(String searchRequest, Pageable pageable);
}
