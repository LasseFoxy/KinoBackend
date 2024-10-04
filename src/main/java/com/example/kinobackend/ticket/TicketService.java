package com.example.kinobackend.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    ITicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket bookTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

}
