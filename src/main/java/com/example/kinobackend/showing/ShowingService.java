package com.example.kinobackend.showing;

import com.example.kinobackend.movie.IMovieRepository;
import com.example.kinobackend.movie.Movie;
import com.example.kinobackend.order.IOrderRepository;
import com.example.kinobackend.order.Order;
import com.example.kinobackend.theater.ITheaterRepository;
import com.example.kinobackend.theater.Theater;
import com.example.kinobackend.ticket.ITicketRepository;
import com.example.kinobackend.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowingService {

    @Autowired
    private IShowingRepository showingRepository;

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private ITheaterRepository theaterRepository;

    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private IOrderRepository orderRepository;

    public void assignMovieToShowings(int movieId, int theaterId, List<Integer> showingIds) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Film ikke fundet"));
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new ResourceNotFoundException("Biografsal ikke fundet"));

        List<Showing> showings = showingRepository.findAllById(showingIds);
        for (Showing showing : showings) {
            if (showing.getMovie() == null) {
                showing.setMovie(movie);
                showingRepository.save(showing);
            } else {
                throw new IllegalArgumentException("Showing " + showing.getShowingId() + " er allerede tildelt en film.");
            }
        }
    }

    public List<Showing> getAvailableShowingsForWeek(int theaterId, LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(6);
        return showingRepository.findByTheaterTheaterIdAndDateBetweenAndMovieIsNull(theaterId, startDate, endDate);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public boolean deleteShowingIfNoTickets(int movieId) {
        List<Showing> showings = showingRepository.findByMovieMovieId(movieId);
        boolean deletedAny = false;
        for (Showing showing : showings) {
            List<Ticket> tickets = ticketRepository.findByShowing(showing);
            if (tickets.isEmpty()) {
                List<Order> orders = orderRepository.findByShowing_ShowingId(showing.getShowingId());
                if (orders.isEmpty()) {
                    showingRepository.delete(showing);
                    deletedAny = true;
                } else {
                    throw new IllegalArgumentException("Cannot delete showing with ID " + showing.getShowingId() + " because it has associated orders.");
                }
            } else {
                throw new IllegalArgumentException("Cannot delete showing with ID " + showing.getShowingId() + " because it has associated tickets.");
            }
        }
        return deletedAny;
    }
}
