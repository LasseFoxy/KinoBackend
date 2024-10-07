package com.example.kinobackend.showing;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowingDTO {
    private int showingId;
    private LocalDate date;
    private LocalTime startTime;
    private int theaterId;
    private String theaterName;

    public ShowingDTO(int showingId, LocalDate date, LocalTime startTime, int theaterId, String theaterName) {
        this.showingId = showingId;
        this.date = date;
        this.startTime = startTime;
        this.theaterId = theaterId;
        this.theaterName = theaterName;
    }
}
