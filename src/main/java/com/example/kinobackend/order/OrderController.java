package com.example.kinobackend.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderDTO orderRequest) {
        System.out.println("Received OrderRequest: " + orderRequest);
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/{orderId}")
    public Order getOrderWithTickets(@PathVariable Long orderId) {
       return orderService.findOrderWithTickets(orderId);
   }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/showing/{showingId}")
    public List <Order> getOrderFromShowingId(@PathVariable Long showingId){
        return orderService.findOrderFromShowingId(showingId);
    }


}
