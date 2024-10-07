package com.example.kinobackend.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.tickets WHERE o.orderId = :orderId")
    Order findOrderWithTickets(int orderId);

    List<Order> findByShowing_ShowingId(int showingId);
}
