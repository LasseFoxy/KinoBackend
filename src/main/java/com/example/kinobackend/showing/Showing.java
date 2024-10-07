package com.example.kinobackend.showing;

import com.example.kinobackend.movie.Movie;
import com.example.kinobackend.theater.Theater;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "showing")
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showing_id")
    private int showingId;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    @JsonIgnoreProperties("showings")
    private Theater theater;

}

