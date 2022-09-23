package com.lotachukwu.movieticketbookingapplication.dto.response;

import lombok.Data;

@Data
public class CreateMovieResponse {

    private String title;
    private String language;
    private String genre;
    private String city;
    private String duration;
}
