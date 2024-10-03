package com.example.kinobackend.Seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatRepository seatRepository;


    @GetMapping("/theater/{theaterId}")
    public List<Seat> getSeatsByTheatre(@PathVariable Long theaterId) {
        return seatRepository.findByTheater_TheaterId(theaterId);
    }

    @GetMapping("/byIds")
    public List<Seat> getSeatsByIds(@RequestParam List<Long> seatIds) {
        return seatRepository.findAllById(seatIds);
    }

}
