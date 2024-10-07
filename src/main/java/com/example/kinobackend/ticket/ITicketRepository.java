package com.example.kinobackend.ticket;


import com.example.kinobackend.order.Order;
import com.example.kinobackend.showing.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByOrder(Order order);
    List<Ticket> findByShowing(Showing showing);
}
