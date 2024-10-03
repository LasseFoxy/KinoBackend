package com.example.kinobackend.showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IShowtimeRepository extends JpaRepository<Showtime, Long> {
    //List<Showtime> findByMovie(Movie movie);
    List<Showtime> findByShowtimeId(Long showtimeId);
}
