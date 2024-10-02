package com.example.kinobackend.service;

import com.example.kinobackend.model.Movie;
import com.example.kinobackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> findMovieById(int id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public Optional<Movie> updateMovie(int id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            updatedMovie.setMovieID(id);
            return movieRepository.save(updatedMovie);
        });
    }

    public boolean deleteMovie(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.delete(movie.get());
            return true;
        }
        return false;
    }

    // Tilføjer billede til filmen
    public void addImageToMovie(int movieId, MultipartFile image) throws IOException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            Movie updatedMovie = movie.get();
            // Sætter billede som byte-array
            updatedMovie.setMovieImage(image.getBytes());
            movieRepository.save(updatedMovie);
        } else {
            throw new RuntimeException("Movie not found");
        }
    }
}