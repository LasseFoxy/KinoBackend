package com.example.kinobackend.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private String customerName;
    private Long showtimeId;
    private List<Long> seatIds;

}

