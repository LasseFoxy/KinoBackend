package com.example.kinobackend.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ImageService imageService;

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
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Create a new movie in database
    @PostMapping("/")
    public ResponseEntity<Movie> create(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    // Update movie by ID
    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable int id, @RequestBody Movie updatedMovie) {
        Optional<Movie> updated = movieService.updateMovie(id, updatedMovie);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    // Upload image for a movie
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
        // Validate file type
        String contentType = image.getContentType();
        if (!contentType.startsWith("image/")) {
            return ResponseEntity.badRequest().body("File must be an image");
        }

        // Validate file size (max 5MB)
        if (image.getSize() > 5 * 1024 * 1024) {  // 5MB
            return ResponseEntity.badRequest().body("File size exceeds 5MB");
        }

        try {
            imageService.saveImage(image);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
}
