package com.lotachukwu.movieticketbookingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    private LocalDateTime dayAndTimeOfMovie;
    private String placeOfShow;
}
