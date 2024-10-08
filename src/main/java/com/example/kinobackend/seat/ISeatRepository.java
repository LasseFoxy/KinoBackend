package com.example.kinobackend.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Integer> {

    @Query("SELECT new com.example.kinobackend.seat.SeatDTO(s.id, s.seatRow, s.seatNumber, t.name) " +
            "FROM Seat s JOIN s.theater t " +
            "WHERE t.id = :theaterId")
    List<SeatDTO> findSeatDTOByTheaterTheaterId(int theaterId);
    Seat getSeatBySeatId(int seatId);

}
