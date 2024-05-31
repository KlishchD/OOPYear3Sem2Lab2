package com.example.oopsem3lab2.core.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trips_description")
@Getter @Setter
public class TripDescription {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "basecost")
    private float baseCost;

    @Column(name = "ishot")
    private boolean isHot;

    @Column(name = "salescost")
    private float salesCost;

    @Column(name = "capacity")
    private int capacity;

    @Override
    public String toString() {
        return "TripDescription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", baseCost=" + baseCost +
                ", isHot=" + isHot +
                ", salesCost=" + salesCost +
                ", capacity=" + capacity +
                '}';
    }
}
