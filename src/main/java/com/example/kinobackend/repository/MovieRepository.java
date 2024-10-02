package com.example.kinobackend.repository;

import com.example.kinobackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findMovieByMovieID(int id);
}

//Med Optional, kan den returnere enten en Movie-instance der matcher MovieId eller være tom (hvis det ikke findes)