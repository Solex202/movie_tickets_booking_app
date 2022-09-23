package com.lotachukwu.movieticketbookingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    @Id
    private String id;
    private String title;
    private Set<String> language;
    private Set<String> genre;
    private String city;
}
