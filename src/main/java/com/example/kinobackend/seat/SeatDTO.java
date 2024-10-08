package com.example.kinobackend.seat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class SeatDTO {
    private int seatId;
    private int seatRow;
    private int seatNumber;
    private String theater;

    public SeatDTO(int seatId, int seatRow, int seatNumber, String theater) {
        this.seatId = seatId;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.theater = theater;
    }

}