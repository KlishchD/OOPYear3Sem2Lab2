package com.example.oopsem3lab2.repositories;

import com.example.oopsem3lab2.core.models.TripDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripDescriptionRepository extends JpaRepository<TripDescription, Integer> {
    @Query("select trip from TripDescription trip where trip.title like concat('%', ?1, '%') or trip.description like concat('%', ?1, '%')")
    List<TripDescription> findAllByPrompt(String prompt);
}
