package com.example.kinobackend.seat;

import com.example.kinobackend.theater.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/byIds")
    public List<Seat> getSeatsByIds(@RequestParam List<Integer> seatIds) {
        return seatService.findAllById(seatIds);
    }
}
