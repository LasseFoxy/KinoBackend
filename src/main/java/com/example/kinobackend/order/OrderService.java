package com.example.kinobackend.order;

import com.example.kinobackend.seat.ISeatRepository;
import com.example.kinobackend.seat.Seat;
import com.example.kinobackend.showing.Showing;
import com.example.kinobackend.showing.IShowingRepository;
import com.example.kinobackend.ticket.Ticket;
import com.example.kinobackend.ticket.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
        @Autowired
        private IOrderRepository orderRepository;

        @Autowired
        private ITicketRepository ticketRepository;

        @Autowired
        private IShowingRepository showingRepository;

        @Autowired
        private ISeatRepository seatRepository;

        @Transactional
        public Order createOrder(OrderDTO orderRequest) {
            Order order = new Order();
            order.setCustomerName(orderRequest.getCustomerName());
            order.setOrderTime(new Date());
            order = orderRepository.save(order);
            Showing showing = showingRepository.findById(orderRequest.getShowingId())
                    .orElseThrow(() -> new RuntimeException("Showing not found"));
            order.setShowing(showing);
            List<Seat> seats = seatRepository.findAllById(orderRequest.getSeatIds());
            for (Seat seat : seats) {
                Ticket ticket = new Ticket();
                ticket.setSeat(seat);
                ticket.setOrder(order);
                ticketRepository.save(ticket);
            }
            List<Ticket> tickets = ticketRepository.findByOrder(order);
            order.setTickets(tickets);
            return order;
        }

    public List <Order> findOrderFromShowingId(Long showingId) {
           return orderRepository.findByShowing_ShowingId(showingId);
    }

    public Order findOrderWithTickets(Long orderId) {
            return orderRepository.findOrderWithTickets(orderId);
    }

    public List<Order> findAll() {
            return orderRepository.findAll();
        }

}


