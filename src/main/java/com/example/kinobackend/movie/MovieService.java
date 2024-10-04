package com.example.kinobackend.movie;

import com.example.kinobackend.movie.Movie;
import com.example.kinobackend.movie.MovieRepository;
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
            updatedMovie.setMovieId(id);
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


}