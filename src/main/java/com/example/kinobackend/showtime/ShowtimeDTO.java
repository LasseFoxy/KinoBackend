package com.example.kinobackend.showtime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowtimeDTO {
    private Long showtimeId;
    private String showtime;
    private String theatreName;
    private Long theatreId;

  /*  public ShowtimeDTO(Showtime showtime) {
        this.showtimeId = showtime.getShowtimeId();
        this.showtime = showtime.getShowtime().toString();
        this.theatreName = showtime.getTheatre().getName();
        this.theatreId = showtime.getTheatre().getTheatreId();
        System.out.println("ShowtimeDTO created with showtimeId: " + this.showtimeId);
    }
 */

}
