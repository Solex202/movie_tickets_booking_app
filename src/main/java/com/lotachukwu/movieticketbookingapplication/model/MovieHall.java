package com.lotachukwu.movieticketbookingapplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MovieHall {

    @Id
    private String name;
    private boolean [] seat;
    private int capacity;
    private int occupiedSeats;



}
