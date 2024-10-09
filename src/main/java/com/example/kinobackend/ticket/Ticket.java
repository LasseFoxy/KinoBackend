package com.example.kinobackend.ticket;

import com.example.kinobackend.order.Order;
import com.example.kinobackend.seat.Seat;
import com.example.kinobackend.showing.Showing;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;

    private Date bookingTime = new Date();

}
