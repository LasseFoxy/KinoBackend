package com.example.kinobackend.Seat;
import com.example.kinobackend.theater.Theater;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "seats")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private char seatRow;
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    @JsonBackReference
    private Theater theater;

}
