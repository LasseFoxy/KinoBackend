package com.example.kinobackend.service;

import com.example.kinobackend.model.Theater;
import com.example.kinobackend.repository.ITheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private ITheaterRepository theaterRepository;


    public Theater saveTheater(Theater theater) {
        return theaterRepository.save(theater);
    }



    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
