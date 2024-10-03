package com.example.kinobackend.showing;

import com.example.kinobackend.showing.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/showings")
public class ShowingController {

    @Autowired
    private ShowingService ShowingService;
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

