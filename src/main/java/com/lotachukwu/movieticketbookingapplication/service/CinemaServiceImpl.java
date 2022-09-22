package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateCinemaRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateCinemaResponse;
import com.lotachukwu.movieticketbookingapplication.exception.CinemaException;
import com.lotachukwu.movieticketbookingapplication.model.Cinema;
import com.lotachukwu.movieticketbookingapplication.model.Movie;
import com.lotachukwu.movieticketbookingapplication.repository.CinemaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CinemaServiceImpl implements CinemaService{

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public CreateCinemaResponse createCinema(CreateCinemaRequest createCinemaRequest) {

        if(locationAlreadyExist(createCinemaRequest.getLocation())){
            throw new CinemaException("Cinema with location already exist");
        }
            log.info("................."+createCinemaRequest.getLocation());
        CreateCinemaResponse response = new CreateCinemaResponse();
        Cinema cinema = new Cinema(createCinemaRequest.getName(),createCinemaRequest.getNumberOfHalls(),createCinemaRequest.getCity(), createCinemaRequest.getLocation());
        cinemaRepository.save(cinema);
        response.setLocation(cinema.getLocation());
        response.setName(cinema.getName());
        response.setNumberOfHalls(cinema.getNumberOfHalls());
        response.setCity(cinema.getCity());
        return response;
    }

    private boolean locationAlreadyExist(String location) {
        return cinemaRepository.existsByLocation(location);
    }
}
