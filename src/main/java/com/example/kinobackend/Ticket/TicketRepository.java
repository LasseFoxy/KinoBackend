package com.example.kinobackend.Ticket;


import com.example.kinobackend.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByOrder(Order order);
}
