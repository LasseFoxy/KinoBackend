package com.example.kinobackend.showing;

import com.example.kinobackend.movie.Movie;
import com.example.kinobackend.movie.MovieService;
import com.example.kinobackend.showing.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showing")
public class ShowingController {

    @Autowired
    private ShowingService ShowingService;

    @Autowired
    MovieService movieService;

    @Autowired ShowingService showingService;
//    @Autowired
//    private MovieService movieService;
//
//    @GetMapping("/movie/{movieId}")
//    public List<Showing> getShowingsByMovie(@PathVariable Long movieId) {
//        Movie movie = movieService.findById(movieId).orElse(null);
//        return ShowingService.findByMovie(movie);
//    }

    @GetMapping
    public List<Showing> getAllShowings() {
        return ShowingService.findAll();
    }
}

