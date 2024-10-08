package com.example.kinobackend.movie;

import com.example.kinobackend.showing.Showing;
import com.example.kinobackend.showing.ShowingDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MovieDTO {
    private int movieId;
    private String title;
    private String genre;
    private List<ShowingDTO> showings = new ArrayList<>();;
    private String posterUrl;
}
