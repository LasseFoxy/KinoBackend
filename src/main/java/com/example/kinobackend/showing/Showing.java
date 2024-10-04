package com.example.kinobackend.showing;
import com.example.kinobackend.movie.Movie;
import com.example.kinobackend.theater.Theater;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "showing")
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showing_id")
    private int showingId;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

}

