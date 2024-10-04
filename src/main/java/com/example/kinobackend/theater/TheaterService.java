package com.example.kinobackend.theater;

import com.example.kinobackend.seat.Seat;
import com.example.kinobackend.seat.ISeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private ITheaterRepository theaterRepository;

    @Autowired
    private ISeatRepository seatRepository;

    public Theater saveTheater(Theater theater) {
        Theater savedTheater = theaterRepository.save(theater);

        // Generer sæder til biografsalen
        generateSeats(savedTheater);

        return savedTheater;
    }

    public Theater getTheaterById(int id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Biografsal med id " + id + " ikke fundet."));
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    /*
    // Opdaterer en biografsal og regenererer sæder
    public Theater updateTheater(int id, Theater theaterDetails) {
        Optional<Theater> optionalTheater = theaterRepository.findById(id);

        if (optionalTheater.isPresent()) {
            Theater existingTheater = optionalTheater.get();
            existingTheater.setName(theaterDetails.getName());
            existingTheater.setNumberOfRows(theaterDetails.getNumberOfRows());
            existingTheater.setSeatsPerRow(theaterDetails.getSeatsPerRow());

            // Gem opdateret biografsal
            Theater updatedTheater = theaterRepository.save(existingTheater);

            // Generer sæder igen efter opdatering
            generateSeats(updatedTheater);

            return updatedTheater;
        } else {
            throw new ResourceNotFoundException("Biografsal med id " + id + " ikke fundet.");
        }
    } */

    // Genererer sæder baseret på rækker og sæder pr. række i den oprettede biografsal
    private void generateSeats(Theater theater) {
        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= theater.getNumberOfRows(); row++) {
            for (int seatNumber = 1; seatNumber <= theater.getSeatsPerRow(); seatNumber++) {
                Seat seat = new Seat();
                seat.setSeatRow(row);
                seat.setSeatNumber(seatNumber);
                seat.setTheater(theater);
                seats.add(seat);
            }
        }

        // Gem alle sæder i batch
        seatRepository.saveAll(seats);
    }

    public List<Seat> getSeatsByTheater(int theaterId) {
        return seatRepository.findByTheaterTheaterId(theaterId);
    }

    // Custom undtagelse, hvis en biografsal ikke findes
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
