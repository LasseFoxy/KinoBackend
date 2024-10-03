package com.example.kinobackend.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.tickets WHERE o.orderId = :orderId")
    Order findOrderWithTickets(Long orderId);

    List<Order> findByShowtime_ShowtimeId(Long showtimeId);
}
