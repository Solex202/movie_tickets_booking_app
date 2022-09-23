package com.lotachukwu.movieticketbookingapplication.repository;

import com.lotachukwu.movieticketbookingapplication.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findBy(String param);

    boolean existsByTitle(String title);

    Movie findMovieByLanguage(String language);

    Movie findMovieByGenre(String genre);

    Movie findMovieByTitle(String title);
}
