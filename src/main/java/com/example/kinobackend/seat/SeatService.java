package com.example.kinobackend.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private ISeatRepository seatRepository;

    public List<Seat> getSeatsByTheater(int theaterId) {
        return seatRepository.findByTheaterTheaterId(theaterId);
    }

    public Seat getSeatById(int seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Sæde med id " + seatId + " ikke fundet."));
    }

    // Exception, hvis et sæde ikke findes
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
