package com.lotachukwu.movieticketbookingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    @Id
    private String id;
    private String title;
    private String language;
    private String genre;
    private String city;
}
