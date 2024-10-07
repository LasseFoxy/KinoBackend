package com.example.kinobackend.theater;

import com.example.kinobackend.seat.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/theater")
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

    @GetMapping("/{theaterId}")
    public ResponseEntity<List<Seat>> getSeatsByTheater(@PathVariable("theaterId") int theaterId) {
        List<Seat> seats = theaterService.getSeatsByTheater(theaterId);
        if (seats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }
    @DeleteMapping("/{theaterId}")
    public ResponseEntity<String> deleteTheater(@PathVariable("theaterId") int theaterId) {
        if (theaterService.deleteTheater(theaterId)) {
            return new ResponseEntity<>("Theater deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Theater not found", HttpStatus.NOT_FOUND);
        }
    }
}
