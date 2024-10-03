package com.example.kinobackend.Seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;


    @GetMapping("/theatre/{theatreId}")
    public List<Seat> getSeatsByTheatre(@PathVariable Long theatreId) {
        return seatRepository.findByTheatre_TheatreId(theatreId);
    }

    @GetMapping("/byIds")
    public List<Seat> getSeatsByIds(@RequestParam List<Long> seatIds) {
        return seatRepository.findAllById(seatIds);
    }

}
