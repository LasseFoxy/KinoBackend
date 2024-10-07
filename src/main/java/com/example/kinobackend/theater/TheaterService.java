package com.example.kinobackend.theater;

import com.example.kinobackend.seat.Seat;
import com.example.kinobackend.seat.ISeatRepository;
import com.example.kinobackend.seat.SeatDTO;
import com.example.kinobackend.showing.IShowingRepository;
import com.example.kinobackend.showing.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private ITheaterRepository theaterRepository;

    @Autowired
    private ISeatRepository seatRepository;

    @Autowired
    private IShowingRepository showingRepository;

    public List<SeatDTO> getSeatsByTheater(int theaterId) {
        return seatRepository.findSeatDTOByTheaterTheaterId(theaterId);
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Theater saveTheater(Theater theater) {
        Theater savedTheater = theaterRepository.save(theater);

        // Generer sæder til biografsalen
        generateSeats(savedTheater);

        // Opret showings 3 måneder frem
        createShowingsForNextThreeMonths(savedTheater);

        return savedTheater;
    }

    private void generateSeats(Theater theater) {
        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= theater.getNumberOfRows(); row++) {
            for (int seatNumber = 1; seatNumber <= theater.getSeatsPerRow(); seatNumber++) {
                Seat seat = new Seat();
                seat.setSeatRow(row);
                seat.setSeatNumber(seatNumber);
                seat.setTheater(theater);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
    }

    private void createShowingsForNextThreeMonths(Theater theater) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusMonths(3);

        for (LocalDate date = today; !date.isAfter(endDate); date = date.plusDays(1)) {
            createShowingsForDate(theater, date);
        }
    }

    private void createShowingsForDate(Theater theater, LocalDate date) {
        List<LocalTime> showingTimes = List.of(
                LocalTime.of(12, 0), // 12:00
                LocalTime.of(16, 0), // 16:00
                LocalTime.of(20, 0)  // 20:00
        );

        for (LocalTime time : showingTimes) {
            Showing newShowing = new Showing();
            newShowing.setTheater(theater);
            newShowing.setDate(date);
            newShowing.setStartTime(time);

            // Gem den nye showing
            showingRepository.save(newShowing);
        }
    }

}
