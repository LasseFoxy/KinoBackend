package com.example.kinobackend.movie;

import com.example.kinobackend.seat.Seat;
import com.example.kinobackend.showing.Showing;
import com.example.kinobackend.theater.Theater;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @Column(length = 225)
    private String title;

    @Column(length = 1000)
    private String description;

    private String genre;
    private int duration;
    private int ageLimit;

    // Setter for movieImage (if not using @Setter annotation)
    @Setter
    private String movieImage;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Showing> showings = new ArrayList<>();


}
