package com.example.kinobackend.Order;

import com.example.kinobackend.Seat.Seat;
import com.example.kinobackend.Seat.SeatRepository;
import com.example.kinobackend.Showtime.Showtime;
import com.example.kinobackend.Showtime.ShowtimeRepository;
import com.example.kinobackend.Ticket.Ticket;
import com.example.kinobackend.Ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private TicketRepository ticketRepository;

        @Autowired
        private ShowtimeRepository showtimeRepository;

        @Autowired
        private SeatRepository seatRepository;

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
}


