package com.example.kinobackend.seat;

import com.example.kinobackend.theater.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private ISeatRepository seatRepository;


    public Seat getSeatById(int seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Sæde med id " + seatId + " ikke fundet."));
    }

    private SeatDTO convertToDto(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setSeatId(seat.getSeatId());
        seatDTO.setSeatRow(seat.getSeatRow());
        seatDTO.setSeatNumber(seat.getSeatNumber());
        // Assuming Theater has a name field
        seatDTO.setTheater(seat.getTheater().getName());
        return seatDTO;
    }

    public List<SeatDTO> findAllById(List<Integer> seatIds) {
        // Fetch the list of Seat entities
        List<Seat> seats = seatRepository.findAllById(seatIds);

        // Manually convert Seat entities to SeatDTO
        return seats.stream()
                .map(this::convertToDto)  // Convert each Seat to SeatDTO
                .collect(Collectors.toList());
    }

    public Seat findById(int seatId){
        return seatRepository.getSeatBySeatId(seatId);
    }
    public List<SeatDTO> findByTheatre_TheatreId(int theaterId) {
        return seatRepository.findSeatDTOByTheaterTheaterId(theaterId);
    }

    // Exception, hvis et sæde ikke findes
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public class SeatMapper {
        // Converts a SeatDTO into a Seat entity
        public static Seat toEntity(SeatDTO seatDTO) {
            Seat seat = new Seat();
            seat.setSeatId(seatDTO.getSeatId());
            seat.setSeatRow(seatDTO.getSeatRow());
            seat.setSeatNumber(seatDTO.getSeatNumber());
            // Assuming you need to map theater separately
            Theater theater = new Theater();
            theater.setName(seatDTO.getTheater());
            seat.setTheater(theater);
            return seat;
        }
    }
}
