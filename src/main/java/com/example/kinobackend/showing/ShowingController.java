package com.example.kinobackend.showing;

import com.example.kinobackend.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/showings")
public class ShowingController {

    @Autowired
    private ShowingService showingService;

    @PostMapping("/{theaterId}/assign-movie/{movieId}")
    public ResponseEntity<?> assignMovieToShowings(
            @PathVariable int theaterId,
            @PathVariable int movieId,
            @RequestBody List<Integer> showingIds) {

        try {
            showingService.assignMovieToShowings(movieId, theaterId, showingIds);
            return ResponseEntity.ok("Film tildelt showings");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{theaterId}/available-showings")
    public ResponseEntity<List<ShowingDTO>> getAvailableShowings(
            @PathVariable int theaterId,
            @RequestParam String startDate) {
        LocalDate start = LocalDate.parse(startDate);
        List<ShowingDTO> availableShowings = showingService.getAvailableShowingsForWeek(theaterId, start)
                .stream()
                .map(showing -> new ShowingDTO(
                        showing.getShowingId(),
                        showing.getDate(),
                        showing.getStartTime(),
                        showing.getTheater().getTheaterId(),
                        showing.getTheater().getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(availableShowings);
    }
    @DeleteMapping("/delete-by-movie/{movieId}")
    public ResponseEntity<?> deleteShowingIfNoTickets(@PathVariable int movieId) {
        boolean deleted = showingService.deleteShowingIfNoTickets(movieId);
        if (deleted) {
            return ResponseEntity.ok("Showing deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Cannot delete showing with tickets");
        }
    }
}