package com.example.kinobackend.showing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowingDTO {
    private Long showingId;
    private String showingTime;
    private String theaterName;
    private Long theaterId;

  /*  public ShowingDTO(Showing showing) {
        this.showingId = showingTime.getShowingId();
        this.showingTime = showingTime.getShowingTime().toString();
        this.theaterName = showingTime.getTheater().getName();
        this.theaterId = showingTime.getTheatre().getTheaterId();
        System.out.println("ShowingTimeDTO created with showingId: " + this.showingId);
    }
 */

}
