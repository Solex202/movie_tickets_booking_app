package com.lotachukwu.movieticketbookingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Cinema {

    @Id
    private String name;
    private String city;
    private String location;
    private MovieHall[] movieHalls;
    private List<Movie> movies;
    private int numberOfHalls;

    public Cinema(String name, int numberOfHalls, String city, String location){
        this.name = name;
        this.numberOfHalls = numberOfHalls;
        this.city = city;
        this.location = location;
        movieHalls = new MovieHall[numberOfHalls];
    }

    public Cinema(String name, int numberOfHalls, String city, String location, MovieHall[] movieHalls, List<Movie> movies){
        this.name = name;
        this.numberOfHalls = numberOfHalls;
        this.city = city;
        this.location = location;
        this.movieHalls = movieHalls;
        this.movies = movies;
    }
}
