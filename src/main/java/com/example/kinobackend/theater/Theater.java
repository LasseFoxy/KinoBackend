package com.example.kinobackend.theater;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@Entity
@Table(name = "theater")
public class Theater {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theater_id;

    /*@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();*/


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int number_of_rows;

    @Column(nullable = false)
    private int seats_per_row;
}