package com.example.kinobackend.order;

import com.example.kinobackend.seat.ISeatRepository;
import com.example.kinobackend.seat.Seat;
import com.example.kinobackend.showtime.Showtime;
import com.example.kinobackend.showtime.IShowtimeRepository;
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
        private IShowtimeRepository showtimeRepository;

        @Autowired
        private ISeatRepository seatRepository;

        @Transactional
        public Order createOrder(OrderDTO orderRequest) {
            Order order = new Order();
            order.setCustomerName(orderRequest.getCustomerName());
            order.setOrderTime(new Date());
            order = orderRepository.save(order);
            Showtime showtime = showtimeRepository.findById(orderRequest.getShowtimeId())
                    .orElseThrow(() -> new RuntimeException("Showtime not found"));
            order.setShowtime(showtime);
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

    public List <Order> findOrderFromShowtimeId(Long showtimeId) {
           return orderRepository.findByShowtime_ShowtimeId(showtimeId);
    }

    public Order findOrderWithTickets(Long orderId) {
            return orderRepository.findOrderWithTickets(orderId);
    }

    public List<Order> findAll() {
            return orderRepository.findAll();
        }

}


