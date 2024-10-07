package com.example.kinobackend.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private String customerName;
    private int showingId;
    private List<Integer> seatIds;

}

