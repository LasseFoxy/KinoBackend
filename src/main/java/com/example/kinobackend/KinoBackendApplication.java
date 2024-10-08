package com.example.kinobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KinoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinoBackendApplication.class, args);
    }

}
