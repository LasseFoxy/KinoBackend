package com.example.kinobackend.movie;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Get all movies
    @GetMapping()
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movies = movieService.findAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    // Get movie by ID
    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> findById(@PathVariable("movieId") int movieId) {
        Optional<Movie> movie = movieService.findMovieById(movieId);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Create a new movie in database
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Movie movie) {
        try {
            Movie createdMovie = movieService.createMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating movie: " + e.getMessage());
        }
    }

    // Update movie by ID
    @PutMapping("/{movieId}")
    public ResponseEntity<Movie> update(@PathVariable("movieId") int movieId, @RequestBody Movie updatedMovie) {
        try {
            Movie updated = movieService.updateMovie(movieId, updatedMovie);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    // Delete movie by ID
    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") int movieId) {
        if (movieService.deleteMovie(movieId)) {
            return ResponseEntity.ok("Movie deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
    }

    @GetMapping("/movieDTOs")
    public List<MovieDTO> getAllMovieDTOs() {
        return movieService.getAllMovieDTOs();
    }

}
