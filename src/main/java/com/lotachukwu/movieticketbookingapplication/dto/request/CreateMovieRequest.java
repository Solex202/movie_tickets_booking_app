package com.lotachukwu.movieticketbookingapplication.dto.request;

import lombok.Data;

@Data
public class CreateMovieRequest {

    private String id;
    private String title;
    private String language;
    private String genre;
    private String city;
}
