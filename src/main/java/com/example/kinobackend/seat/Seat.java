package com.example.kinobackend.seat;

import com.example.kinobackend.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Data;
import com.example.kinobackend.theater.Theater;

@Data
@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @Column(name = "seat_row", nullable = false)
    private int seatRow;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @OneToOne(mappedBy = "seat")
    private Ticket ticket;
}
