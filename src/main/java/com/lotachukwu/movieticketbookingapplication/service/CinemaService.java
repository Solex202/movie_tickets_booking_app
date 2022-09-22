package com.lotachukwu.movieticketbookingapplication.service;

import com.lotachukwu.movieticketbookingapplication.dto.request.CreateCinemaRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.CreateCinemaResponse;

public interface CinemaService {


    public CreateCinemaResponse createCinema(CreateCinemaRequest createCinemaRequest);
}
