package com.example.kinobackend.model;

import com.example.kinobackend.controller.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieID;
    private String title;
    @Column(length = 1000)
    private String description;
    private String genre;
    private int duration;
    private int ageLimit;


    // Gemmer billede som byte-array (f.eks. som billede-data)
    @Column(length = 1000)
    private byte[] movieImage;  // Gemmer billede som byte-array

    // Liste af billeder knyttet til filmen
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")  // Relaterer til 'movie_id' i Image-tabelen
    private List<Image> images;  // Liste af billeder knyttet til filmen

}
