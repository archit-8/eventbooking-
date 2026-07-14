package com.archit.eventbooking.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    @ManyToOne
    private Event event;
    private int seatsBooked;
}
