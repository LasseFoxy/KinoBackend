package com.example.kinobackend.scheduler;

import com.example.kinobackend.theater.ITheaterRepository;
import com.example.kinobackend.theater.Theater;
import com.example.kinobackend.theater.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ShowingServiceScheduler {

    @Autowired
    private ITheaterRepository theaterRepository;

    @Autowired
    private TheaterService theaterService;

    @Scheduled(cron = "0 0 0 * * ?", zone = "Europe/Copenhagen")
    public void scheduleDailyShowingsUpdate() {
        LocalDate newDate = LocalDate.now().plusMonths(3);

        List<Theater> theaters = theaterRepository.findAll();
        for (Theater theater : theaters) {
            theaterService.createShowingsForDate(theater, newDate);
        }
    }
}
