package com.example.kinobackend.repository;

import com.example.kinobackend.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITheaterRepository extends JpaRepository<Theater, Integer> {
}
