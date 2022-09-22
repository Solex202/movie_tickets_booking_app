package com.lotachukwu.movieticketbookingapplication.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Show {

    private LocalDateTime movieTime;
    private String placeOfShow;
}
