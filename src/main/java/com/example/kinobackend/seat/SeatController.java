package com.example.kinobackend.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Seat>> getSeatsByTheater(@PathVariable("theaterId") int theaterId) {
        List<Seat> seats = seatService.getSeatsByTheater(theaterId);
        if (seats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }
}
