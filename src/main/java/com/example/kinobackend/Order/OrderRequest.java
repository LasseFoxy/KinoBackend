package com.example.kinobackend.Order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class OrderRequest {
    private String customerName;
    private Long showtimeId;
    private List<Long> seatIds;

}

