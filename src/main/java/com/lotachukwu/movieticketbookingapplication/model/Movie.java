package com.lotachukwu.movieticketbookingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    private String id;
    private String title;
    private String language;
    private String genre;
    private String city;
}
