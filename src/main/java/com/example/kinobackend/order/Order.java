package com.example.kinobackend.order;

import com.example.kinobackend.showing.Showing;
import com.example.kinobackend.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name ="orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
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
