package com.example.oopsem3lab2.repositories;

import com.example.oopsem3lab2.core.models.TripInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripInstanceRepository extends JpaRepository<TripInstance, Integer> {
    @Query("select instance from TripInstance instance where instance.tripId in ?1")
    List<TripInstance> findAllByTripIds(Iterable<Integer> ids);
}
