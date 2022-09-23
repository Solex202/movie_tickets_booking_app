package com.lotachukwu.movieticketbookingapplication.dto.request;

import lombok.Data;

@Data
public class SearchMovieRequest {

    private String title;
    private String language;
    private String genre;
    private String cityName;


}
