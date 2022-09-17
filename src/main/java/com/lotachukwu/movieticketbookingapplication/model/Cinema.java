package com.lotachukwu.movieticketbookingapplication.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Cinema {

    @Id
    private String name;
    private String city;
    private int seatNumbers;
    private String location;
}
