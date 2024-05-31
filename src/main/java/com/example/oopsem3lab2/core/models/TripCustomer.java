package com.example.oopsem3lab2.core.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trip_customers")
@Getter @Setter
public class TripCustomer {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "username")
    String username;

    @Column(name = "status")
    TripPaymentStatus status;

    @Override
    public String toString() {
        return "TripCustomer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
