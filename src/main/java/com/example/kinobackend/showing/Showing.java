package com.example.kinobackend.showing;
import com.example.kinobackend.theater.Theater;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "showing")
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showingId;

//    @ManyToOne
//    @JoinColumn(name = "movie_id")
//    private Movie movie;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;
//
//    private Date showing;
//
//    public Showing(Movie movie, Theater theater, Date showing) {
//        this.movie = movie;
//        this.theater = theater;
//        this.showing = showing;
//    }
}
