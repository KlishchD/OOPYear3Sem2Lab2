package com.example.oopsem3lab2.repositories;

import com.example.oopsem3lab2.core.models.TripCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripCustomersRepository extends JpaRepository<TripCustomer, Integer> {
    TripCustomer findByIdAndUsername(int id, String username);

    @Query("select customer from TripCustomer customer where customer.id in ?1 and customer.username = ?2")
    List<TripCustomer> findAllByIdsAndUsername(Iterable<Integer> id, String username);
}
