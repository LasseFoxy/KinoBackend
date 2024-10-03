package com.example.kinobackend.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    //List<Showtime> findByMovie(Movie movie);
    List<Showtime> findByShowtimeId(Long showtimeId);
}
