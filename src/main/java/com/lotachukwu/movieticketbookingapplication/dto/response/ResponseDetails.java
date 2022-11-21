package com.lotachukwu.movieticketbookingapplication.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDetails {

    private CreateMovieResponse message;
    private  boolean isSuccessful;
    private String exceptionMessage;
}

