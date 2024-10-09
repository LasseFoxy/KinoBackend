package com.example.kinobackend.showing;
import com.example.kinobackend.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IShowingRepository extends JpaRepository<Showing, Integer> {
    List<Showing> findByMovie(Movie movie);
    List<Showing> findByShowingId(int showingId);

    @Query("SELECT s FROM Showing s WHERE s.theater.theaterId = :theaterId " +
            "AND s.date BETWEEN :startDate AND :endDate " +
            "AND (s.date > :currentDate OR (s.date = :currentDate AND s.startTime > :currentTime)) " +
            "AND s.movie IS NULL")
    List<Showing> findAvailableShowingsForWeek(
            @Param("theaterId") int theaterId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("currentDate") LocalDate currentDate,
            @Param("currentTime") LocalTime currentTime);



}
