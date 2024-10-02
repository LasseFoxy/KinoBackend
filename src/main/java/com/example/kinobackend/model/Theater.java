package com.example.kinobackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "theaters")
public class Theater {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theater_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int number_of_rows;

    @Column(nullable = false)
    private int seats_per_row;

    // Getters and setters
    public int getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(int theater_id) {
        this.theater_id = theater_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_rows() {
        return number_of_rows;
    }

    public void setNumber_of_rows(int number_of_rows) {
        this.number_of_rows = number_of_rows;
    }

    public int getSeats_per_row() {
        return seats_per_row;
    }

    public void setSeats_per_row(int seats_per_row) {
        this.seats_per_row = seats_per_row;
    }
}
