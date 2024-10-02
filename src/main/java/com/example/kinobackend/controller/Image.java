package com.example.kinobackend.controller;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String imageType;


    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    // Gemmer billede som byte-array
    @Lob
    @Column(nullable = false)
    private byte[] data;  // Billeddata som byte-array

}