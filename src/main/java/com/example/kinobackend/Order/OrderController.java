package com.example.kinobackend.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("Received OrderRequest: " + orderRequest);
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/{orderId}")
    public Order getOrderWithTickets(@PathVariable Long orderId) {
       return orderRepository.findOrderWithTickets(orderId);
   }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/showtimes/{showtimeId}")
    public List <Order> getOrderFromShowtimeId(@PathVariable Long showtimeId){
        return orderService.findOrderFromShowtimeId(showtimeId);
    }


}
