package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateCinemaRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateCinemaResponse;
import com.lotachukwu.movieticketbookingapplication.model.Cinema;

import java.util.Collection;
import java.util.List;

public interface CinemaService {


    public CreateCinemaResponse createCinema(CreateCinemaRequest createCinemaRequest);

    void deleteAll();

    List<Cinema> getListOfCinema();

    List<String> getListOfAllCinemaCities();
}
