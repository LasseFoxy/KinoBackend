package com.example.kinobackend.showtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeService {

    @Autowired
    private IShowtimeRepository showtimeRepository;

    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }
}

