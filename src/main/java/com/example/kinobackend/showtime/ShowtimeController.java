package com.example.kinobackend.showtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;
//    @Autowired
//    private MovieService movieService;
//
//    @GetMapping("/movie/{movieId}")
//    public List<Showtime> getShowtimesByMovie(@PathVariable Long movieId) {
//        Movie movie = movieService.findById(movieId).orElse(null);
//        return showtimeService.findByMovie(movie);
//    }

    @GetMapping
    public List<Showtime> getAllShowtimes() {
        return showtimeService.findAll();
    }
}

