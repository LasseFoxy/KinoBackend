package com.example.kinobackend.movie;

import com.example.kinobackend.showing.IShowingRepository;
import com.example.kinobackend.showing.Showing;
import com.example.kinobackend.showing.ShowingDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IShowingRepository showingRepository;


    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> findMovieById(int movieId) {
        return movieRepository.findById(movieId);
    }

    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie updateMovie(int movieId, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with ID " + movieId + " not found"));

        updatedMovie.setMovieId(movieId);
        return movieRepository.save(updatedMovie);
    }


    public boolean deleteMovie(int movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return true;
        }
        return false;
    }


    public List<MovieDTO> getAllMovieDTOs() {
        List<Movie> movies = movieRepository.findAll();

        return movies.stream().map(movie -> {
            // Fetch showtimes for the current movie
            List<Showing> showtimes = showingRepository.findByMovie(movie);

            // Create a new MovieDTO and map showtimes to it
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setMovieId(movie.getMovieId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setGenre(movie.getGenre());
            movieDTO.setPosterUrl("/api/movies/" + movie.getMovieId() + "/poster");

            // Map showtimes from Movie entity to ShowtimeDTO
            List<ShowingDTO> showtimeDTOs = showtimes.stream().map(showtime -> {
                ShowingDTO showingDTO = new ShowingDTO();
                showingDTO.setTheaterName(showtime.getTheater().getName());
                showingDTO.setShowingId(showtime.getShowingId());
                showingDTO.setTheaterId(showtime.getTheater().getTheaterId());  // Include the theatreId
                showingDTO.setStartTime(showtime.getStartTime());
                showingDTO.setDate(showtime.getDate());  // Convert to appropriate format
                return showingDTO;
            }).collect(Collectors.toList());

            movieDTO.setShowings(showtimeDTOs);

            return movieDTO;
        }).collect(Collectors.toList());
    }
}