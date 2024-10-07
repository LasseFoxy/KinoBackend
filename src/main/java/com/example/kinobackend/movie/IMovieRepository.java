package com.example.kinobackend.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findMovieByMovieId(int id);
}

//Med Optional, kan den returnere enten en Movie-instance der matcher MovieId eller være tom (hvis det ikke findes)