package com.example.kinobackend.showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IShowingRepository extends JpaRepository<Showing, Long> {
    //List<Showing> findByMovie(Movie movie);
    List<Showing> findByShowingId(Long showingId);
}
