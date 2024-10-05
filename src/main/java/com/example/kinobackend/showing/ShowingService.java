package com.example.kinobackend.showing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowingService {

    @Autowired
    private IShowingRepository showingRepository;

    public List<Showing> findAll() {
        return showingRepository.findAll();
    }

}

