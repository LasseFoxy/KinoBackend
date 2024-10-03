package com.example.kinobackend.seat;

import com.example.kinobackend.theater.TheaterService;
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
    @Autowired
    private TheaterService theaterService;

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Seat>> getSeatsByTheater(@PathVariable("theaterId") int theaterId) {
        List<Seat> seats = seatService.getSeatsByTheater(theaterId);
        if (seats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @GetMapping("/theater/{theaterId}")
    public List<Seat> getSeatsByTheatre(@PathVariable int theaterId) {
        return seatService.getSeatsByTheater(theaterId);
    }

    @GetMapping("/byIds")
    public List<Seat> getSeatsByIds(@RequestParam List<Integer> seatIds) {
        return seatService.findAllById(seatIds);
    }
}
