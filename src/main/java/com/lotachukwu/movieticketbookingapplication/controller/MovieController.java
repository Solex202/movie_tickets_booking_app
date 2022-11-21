package com.lotachukwu.movieticketbookingapplication.controller;


import com.lotachukwu.movieticketbookingapplication.dto.request.CreateMovieRequest;
import com.lotachukwu.movieticketbookingapplication.dto.response.ResponseDetails;
import com.lotachukwu.movieticketbookingapplication.exception.MovieException;
import com.lotachukwu.movieticketbookingapplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("addMovie")
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieRequest request){
        try {
            ResponseDetails response = ResponseDetails.builder()
                            .message(movieService.createMovie(request))
                                    .isSuccessful(true)
                                            .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(MovieException e){
            ResponseDetails response = ResponseDetails.builder()
                    .exceptionMessage(e.getMessage())
                    .isSuccessful(false)
                    .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
