package com.example.kinobackend.controller;

import com.example.kinobackend.model.Movie;
import com.example.kinobackend.repository.MovieRepository;
import com.example.kinobackend.service.ImageService;
import com.example.kinobackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {


    @Autowired
    private MovieService movieService;
    @Autowired
    private ImageService imageService;


    @GetMapping()
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movies = movieService.findAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable int id) {
        Optional<Movie> movie = movieService.findMovieById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Movie> create(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.ok("Movie deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable int id, @RequestBody Movie updatedMovie) {
        Optional<Movie> updated = movieService.updateMovie(id, updatedMovie);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Endpoint til at uploade et billede
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            // Gem billede til serveren, database, eller cloud
            byte[] imageBytes = image.getBytes();

            // Her kan du gemme billedet i filsystemet eller database
            // Eksempel: movieService.saveImage(imageBytes);

            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image: " + e.getMessage());
        }
    }
}