package com.example.kinobackend.seat;

import com.example.kinobackend.theater.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/seat")
public class SeatController {



    @Autowired
    private SeatService seatService;

    @GetMapping("/theater/{theaterId}")
    public List<SeatDTO> getSeatsByTheater(@PathVariable int theaterId) {
        return seatService.findByTheatre_TheatreId(theaterId);
    }

    @GetMapping("/byIds")
    public List<SeatDTO> getSeatsByIds(@RequestParam List<Integer> seatIds) {
        return seatService.findAllById(seatIds);
    }

}
