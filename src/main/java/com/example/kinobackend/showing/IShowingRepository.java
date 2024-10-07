package com.example.kinobackend.showing;
import com.example.kinobackend.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IShowingRepository extends JpaRepository<Showing, Integer> {
    List<Showing> findByMovie(Movie movie);
    List<Showing> findByShowingId(int showingId);
    List<Showing> findByTheaterTheaterIdAndDateBetweenAndMovieIsNull(int theaterId, LocalDate startDate, LocalDate endDate);

}
