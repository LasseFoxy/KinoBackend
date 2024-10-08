package com.example.kinobackend.movie;

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
    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable int id) {
        Optional<Movie> movie = movieService.findMovieById(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new movie in database
    @PostMapping("/")
    public ResponseEntity<Movie> create(@RequestParam("movie") String movieJson,
                                        @RequestParam("image") MultipartFile image) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Movie movie = objectMapper.readValue(movieJson, Movie.class);
            Movie createdMovie = movieService.createMovie(movie, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Handle the error appropriately
        }
    }

    // Update movie by ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id,
                                         @RequestParam("movie") String movieJson,
                                         @RequestParam("image") MultipartFile image) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Movie updatedMovie = objectMapper.readValue(movieJson, Movie.class);
            Optional<Object> movie = movieService.updateMovie(id, updatedMovie, image);
            return movie.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Handle the error appropriately
        }
    }

    // Delete movie by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id) {
        if (movieService.deleteMovie(id)) {
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
