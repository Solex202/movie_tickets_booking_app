package com.lotachukwu.movieticketbookingapplication.repository;

import com.lotachukwu.movieticketbookingapplication.model.Cinema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends MongoRepository<Cinema, String> {


    boolean existsByLocation(String location);
}
