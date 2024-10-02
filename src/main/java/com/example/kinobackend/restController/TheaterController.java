package com.example.kinobackend.restController;

import com.example.kinobackend.model.Theater;
import com.example.kinobackend.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater createdTheater = theaterService.saveTheater(theater);
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }


}
