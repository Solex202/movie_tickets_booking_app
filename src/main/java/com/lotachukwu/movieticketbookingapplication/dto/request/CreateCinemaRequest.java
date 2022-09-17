package com.lotachukwu.movieticketbookingapplication.dto.request;

import lombok.Data;

@Data
public class CreateCinemaRequest {

    private String name;
    private String city;
    private int seatNumbers;
    private String location;
}
