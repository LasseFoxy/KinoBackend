package com.example.kinobackend.theater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;


    @GetMapping
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }


    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater createdTheater = theaterService.saveTheater(theater);
        return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable("id") int id, @RequestBody Theater theaterDetails) {
        Theater updatedTheater = theaterService.updateTheater(id, theaterDetails);
        return new ResponseEntity<>(updatedTheater, HttpStatus.OK);
    }


}
