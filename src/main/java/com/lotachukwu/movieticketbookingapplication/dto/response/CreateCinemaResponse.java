package com.lotachukwu.movieticketbookingapplication.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCinemaResponse {

    private String name;
    private String city;
    private String location;
    private List<String> movies = new ArrayList<>();
    private int numberOfHalls;
}
