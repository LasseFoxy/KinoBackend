package com.example.kinobackend.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByTheaterTheaterId(int theaterId);
}
