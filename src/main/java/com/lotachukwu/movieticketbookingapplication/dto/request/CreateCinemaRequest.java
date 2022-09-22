package com.lotachukwu.movieticketbookingapplication.dto.request;

import com.lotachukwu.movieticketbookingapplication.model.Movie;
import com.lotachukwu.movieticketbookingapplication.model.MovieHall;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCinemaRequest {

    private String name;
    private String city;
    private String location;
//    private MovieHall[] movieHalls;
    private List<String> movies = new ArrayList<>();
    private int numberOfHalls;
}
