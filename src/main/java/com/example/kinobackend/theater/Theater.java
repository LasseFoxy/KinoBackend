package com.example.kinobackend.theater;

import com.example.kinobackend.seat.Seat;
import com.example.kinobackend.showing.Showing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterId;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Showing> showings = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int numberOfRows;

    @Column(nullable = false)
    private int seatsPerRow;
}
