package com.example.kinobackend.order;

import com.example.kinobackend.showing.Showing;
import com.example.kinobackend.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    private String customerName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime = new Date();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;

}
