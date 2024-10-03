package com.example.kinobackend.theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private ITheaterRepository theaterRepository;


    public Theater saveTheater(Theater theater) {
        return theaterRepository.save(theater);
    }



    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }



    public Theater updateTheater(int id, Theater theaterDetails) {
        Optional<Theater> optionalTheater = theaterRepository.findById(id);

        if (optionalTheater.isPresent()) {
            Theater existingTheater = optionalTheater.get();
            existingTheater.setName(theaterDetails.getName());
            existingTheater.setNumber_of_rows(theaterDetails.getNumber_of_rows());
            existingTheater.setSeats_per_row(theaterDetails.getSeats_per_row());

            return theaterRepository.save(existingTheater);
        } else {
            throw new ResourceNotFoundException("Biografsal med id " + id + " ikke fundet.");
        }
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
