package com.example.kinobackend.Showtime;
import com.example.seatpickerbackend.Movie.Movie;
import com.example.seatpickerbackend.Theatre.Theatre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "showtimes")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showtimeId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    private Date showtime;

    public Showtime(Movie movie, Theatre theatre, Date showtime) {
        this.movie = movie;
        this.theatre = theatre;
        this.showtime = showtime;
    }
}
