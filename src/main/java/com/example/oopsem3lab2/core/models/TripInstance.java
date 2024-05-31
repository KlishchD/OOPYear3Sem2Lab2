package com.example.oopsem3lab2.core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "trips_instances")
@Getter @Setter
public class TripInstance {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "time")
    private LocalDate time;

    @Column(name = "tripid")
    private int tripId;

    @Transient
    private TripPaymentStatus status = TripPaymentStatus.NotPaid;

    @Transient
    private TripDescription trip;

    @Transient
    private User buyer;

    @Override
    public String toString() {
        return "TripInstance{" +
                "trip=" + trip +
                ", id=" + id +
                ", time=" + time +
                '}';
    }

    public static String getCreationTableCreationSql() {
        String tripInstanceCreationQuery = "CREATE TABLE IF NOT EXISTS trips_instances (" +
                "id SERIAL PRIMARY KEY, " +
                "tripId INTEGER, " +
                "time DATE);";

        String tripCustomersCreationQuery = "CREATE TABLE IF NOT EXISTS trip_customers (id INTEGER, " +
                "username VARCHAR(36), " +
                "status INTEGER);";

        return tripInstanceCreationQuery + tripCustomersCreationQuery;
    }
}
